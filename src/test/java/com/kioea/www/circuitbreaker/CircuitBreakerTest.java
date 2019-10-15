package com.kioea.www.circuitbreaker;

import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kioea.www.circuitbreaker.exception.CircuitBreakerOpenException;
import com.kioea.www.circuitbreaker.handler.ProxyFactory;
import com.kioea.www.circuitbreaker.service.DemoService;
import com.kioea.www.circuitbreaker.service.DemoServiceImpl;

/**
 * Created by patterncat on 2016/4/21.
 */
public class CircuitBreakerTest {
	 private static final Logger logger = LoggerFactory.getLogger(CircuitBreaker.class);
	
    DemoService demoService;

    @Before
    public void prepare(){
        demoService = ProxyFactory.proxyBean(new DemoServiceImpl());
    }

    @Test
    public void testSucc(){
        for(int i = 0;i<100;i++){
            System.out.println(demoService.getUuid(1));
        }
    }

    @Test(expected = CircuitBreakerOpenException.class)
    public void testOpen(){
        for(int i=0;i<10;i++){
            try{
                demoService.getUuid(2);
            }catch (Exception e){
            }
        }
        demoService.getUuid(1);
    }

    @Test
    public void testFailWindow() throws InterruptedException {
        for(int i=0;i<6;i++){
            Thread.sleep(13 * 1000);
            try{
                demoService.getUuid(2);
            }catch (Exception e){

            }
        }
        try{
            demoService.getUuid(2);
        }catch (Exception e){

        }
    }

    @Test
    public void testHalfOpen2Open() throws InterruptedException {
        for(int i=0;i<6;i++){
            try{
                demoService.getUuid(2);
            }catch (Exception e){

            }
        }
        //into half open
        TimeUnit.SECONDS.sleep(6);
        try{
            demoService.getUuid(2);
        }catch (Exception e){

        }
        //into open
    }

    @Test
    public void testHalfOpen2Close() throws InterruptedException {
        for(int i=0;i<6;i++){
            try{
                demoService.getUuid(2);
            }catch (Exception e){

            }
        }
        //into half open
        TimeUnit.SECONDS.sleep(60);
        for(int i=0;i<6;i++){
            demoService.getUuid(1);
        }
        //into close
        TimeUnit.SECONDS.sleep(60);
        for(int i=0;i<6;i++){
            try{
                demoService.getUuid(2);
            }catch (Exception e){

            }
        }
    }

    @Test
    public void testNoTripException(){
        for(int i=0;i<6;i++){
            try{
                demoService.illegalEx(2);
            }catch (Exception e){

            }
        }
        // still close
        try{
            demoService.illegalEx(2);
        }catch (Exception e){

        }
    }
}
