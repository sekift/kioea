package com.kioea.www.circuitbreaker.service;

import com.kioea.www.circuitbreaker.annotation.GuardByCircuitBreaker;

/**
 * Created by patterncat on 2016/4/21.
 */
public interface DemoService {

    @GuardByCircuitBreaker(noTripExceptions = {})
    public String getUuid(int idx);

    @GuardByCircuitBreaker(noTripExceptions = {IllegalArgumentException.class})
    public void illegalEx(int idx);
}
