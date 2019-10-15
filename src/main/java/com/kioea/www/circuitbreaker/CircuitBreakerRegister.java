package com.kioea.www.circuitbreaker;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by patterncat on 2016/4/21.
 */
public class CircuitBreakerRegister {
    private static ConcurrentHashMap<String, CircuitBreaker> breakers = new ConcurrentHashMap<String, CircuitBreaker>();

    public static CircuitBreaker get(String key){
        return breakers.get(key);
    }

    public static void putIfAbsent(String key,CircuitBreaker circuitBreaker){
        breakers.putIfAbsent(key,circuitBreaker);
    }
}
