package ratelimitter.service.impl;

import ratelimitter.entity.ClientRequests;
import ratelimitter.entity.TokenBucket;
import ratelimitter.service.RateLimiter;

import java.time.Instant;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class FixedWindowCounterRateLimiter extends RateLimiter {
    private ConcurrentHashMap<String, TokenBucket> tokenMap;

    public FixedWindowCounterRateLimiter(int noOfRequestAllowed, int timeIntervalInSec) {
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
            var now = Instant.now();
            tokenMap.putIfAbsent(clientId, new TokenBucket(now, noOfRequestAllowed));
            TokenBucket bucket = tokenMap.get(clientId);
            if (now.getEpochSecond() - bucket.getLastRefillTimestamp().getEpochSecond() >= timeIntervalInSec) {
                bucket.setAvailableToken(noOfRequestAllowed);
                bucket.setLastRefillTimestamp(now);
            }
            if (bucket.getAvailableToken() > 0) {
                bucket.setAvailableToken(bucket.getAvailableToken() - 1);
                return true;
            }
        }
        return false;
    }
}
