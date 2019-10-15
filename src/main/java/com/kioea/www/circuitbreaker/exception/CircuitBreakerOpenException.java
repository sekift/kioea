package com.kioea.www.circuitbreaker.exception;

/**
 * Created by patterncat on 2016/4/21.
 */
public class CircuitBreakerOpenException extends CircuitBreakerException {
	private static final long serialVersionUID = -4000351220345755092L;

	public CircuitBreakerOpenException(String message, Throwable cause) {
		super("The operation " + message + " has too many failures, tripping circuit breaker.", cause);
	}

	public CircuitBreakerOpenException(String message) {
		super("The operation " + message + " has too many failures, tripping circuit breaker.");
	}
}
