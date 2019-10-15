package com.kioea.www.circuitbreaker.exception;

/**
 * Created by patterncat on 2016/4/21.
 */
public class CircuitBreakerHalfOpenException extends CircuitBreakerException{
	private static final long serialVersionUID = 4057073117161189609L;

	public CircuitBreakerHalfOpenException(String message) {
        super(message);
    }

    public CircuitBreakerHalfOpenException(String message, Throwable cause) {
        super(message, cause);
    }
}
