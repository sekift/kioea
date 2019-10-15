package com.kioea.www.circuitbreaker.handler;

/**
 * Created by patterncat on 2016/4/21.
 */
public class ProxyFactory {
    @SuppressWarnings("unchecked")
	public static <T> T proxyBean(Object target){
        return (T) new CircuitBreakerInvocationHandler(target).proxy();
    }
}
