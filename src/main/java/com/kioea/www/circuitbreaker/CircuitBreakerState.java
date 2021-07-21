package com.kioea.www.circuitbreaker;

/**
 * Created by patterncat on 2016/4/20.
 */
public enum CircuitBreakerState {
    // working normally, calls are transparently passing through
    CLOSED,
    // method calls are being intercepted and CircuitBreakerExceptions are being thrown instead
    OPEN,
    // method calls are passing through; if another blacklisted exception is thrown, reverts back to OPEN
    HALF_OPEN
}
