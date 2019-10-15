package com.kioea.www.circuitbreaker.exception;

/**
 * Created by patterncat on 2016/4/21.
 */
public class CircuitBreakerException extends RuntimeException{

	private static final long serialVersionUID = 8568853157666164621L;

	public CircuitBreakerException(String message) {
        super(message);
    }

    public CircuitBreakerException(String message, Throwable cause) {
        super(message, cause);
    }
}
