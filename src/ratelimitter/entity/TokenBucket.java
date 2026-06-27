package ratelimitter.entity;

import java.time.Instant;

public class TokenBucket {
    private Instant lastRefillTimestamp;
    private int availableToken;

    public TokenBucket(Instant lastRefillTimestamp, int availableToken) {
        this.lastRefillTimestamp = lastRefillTimestamp;
        this.availableToken = availableToken;
    }

    public Instant getLastRefillTimestamp() {
        return lastRefillTimestamp;
    }

    public void setLastRefillTimestamp(Instant lastRefillTimestamp) {
        this.lastRefillTimestamp = lastRefillTimestamp;
    }

    public int getAvailableToken() {
        return availableToken;
    }

    public void setAvailableToken(int availableToken) {
        this.availableToken = availableToken;
    }

    @Override
    public String toString() {
        return "TokenBucket{" +
                "lastRefillTimestamp=" + lastRefillTimestamp +
                ", tokenToAdd=" + availableToken +
                '}';
    }
}
