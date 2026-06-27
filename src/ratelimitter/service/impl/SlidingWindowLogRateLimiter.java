package ratelimitter.service.impl;

import ratelimitter.entity.ClientRequests;
import ratelimitter.service.RateLimiter;

import java.time.LocalDateTime;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

public class SlidingWindowLogRateLimiter extends RateLimiter {
    private ConcurrentHashMap<String, BlockingQueue<LocalDateTime>> slidingWindowMap;

    public SlidingWindowLogRateLimiter(int noOfRequestAllowed, int timeIntervalInSec) {
        super(noOfRequestAllowed, timeIntervalInSec);
        slidingWindowMap = new ConcurrentHashMap<>();
    }

    @Override
    public boolean rateLimit(ClientRequests requests) {
        String clientId = requests.getClientId();
        if (clientId.isBlank()) {
            throw new IllegalArgumentException("Illegal Argument Found");
        }
        boolean isAdded = false;
        var pq = slidingWindowMap.computeIfAbsent(clientId, id -> new ArrayBlockingQueue<>(noOfRequestAllowed));
        synchronized (pq) {
            var now = LocalDateTime.now();
            while (!pq.isEmpty() && !pq.peek().plusSeconds(timeIntervalInSec).isAfter(now)) {
                pq.poll();
            }
            isAdded = pq.offer(now);
        }
        return isAdded;
    }
}
