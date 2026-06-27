package ratelimitter;

import ratelimitter.entity.ClientRequests;
import ratelimitter.service.RateLimiter;
import ratelimitter.service.impl.FixedWindowCounterRateLimiter;

public class RateLimiterMain {
    public static void main(String[] args) throws InterruptedException {
        RateLimiter limiter =
                new FixedWindowCounterRateLimiter(5, 60); // 5 requests per minute

        ClientRequests request = new ClientRequests("USER1","Debmalya");

        for (int i = 1; i <= 7; i++) {
            System.out.println(
                    "Request " + i + " : "
                            + limiter.rateLimit(request)
            );
        }
    }
}
