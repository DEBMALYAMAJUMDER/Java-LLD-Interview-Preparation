package ratelimitter.service;

import ratelimitter.entity.ClientRequests;

public abstract class RateLimiter {
    protected int noOfRequestAllowed;
    protected int timeIntervalInSec;

    public RateLimiter(int noOfRequestAllowed, int timeIntervalInSec) {
        this.noOfRequestAllowed = noOfRequestAllowed;
        this.timeIntervalInSec = timeIntervalInSec;
    }

    public abstract boolean rateLimit(ClientRequests requests);

}
