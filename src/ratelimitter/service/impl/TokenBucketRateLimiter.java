package ratelimitter.service.impl;

import ratelimitter.entity.ClientRequests;
import ratelimitter.entity.TokenBucket;
import ratelimitter.exception.TooManyRequestsException;
import ratelimitter.service.RateLimiter;

import java.time.Instant;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

public class TokenBucketRateLimiter extends RateLimiter {
    private ConcurrentHashMap<String, TokenBucket> tokenMap;

    public TokenBucketRateLimiter(int noOfRequestAllowed, int timeIntervalInSec) {
        super(noOfRequestAllowed, timeIntervalInSec);
        tokenMap = new ConcurrentHashMap<>();
    }

    @Override
    public boolean rateLimit(ClientRequests requests) {
        String clientId = requests.getClientId();
        if (clientId.isBlank()) {
            throw new IllegalArgumentException("Illegal Argument Found");
        }
        synchronized (clientId.intern()) {
            if (tokenMap.containsKey(clientId)) {
                var tokenBucket = tokenMap.get(clientId);
                Instant now = Instant.now();
                int elapseTime = (int) (now.getEpochSecond() - tokenBucket.getLastRefillTimestamp().getEpochSecond());
                int tokenToAdd = (int) Math.floor(elapseTime * (noOfRequestAllowed * 1.0 / timeIntervalInSec));
                int avlToken = Math.min(noOfRequestAllowed, tokenBucket.getAvailableToken() + tokenToAdd);
                if (avlToken > 0) {
                    tokenBucket.setAvailableToken(avlToken - 1);
                    return true;
                }
            } else {
                tokenMap.putIfAbsent(clientId, new TokenBucket(Instant.now(), noOfRequestAllowed - 1));
                return true;
            }
        }
        return false;
    }
}
