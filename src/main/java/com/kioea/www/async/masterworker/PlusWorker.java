package com.kioea.www.async.masterworker;

/**
 * @author:sekift
 * @time:2017-7-24 下午05:34:10
 * @version:
 */
public class PlusWorker extends Worker {

    @Override
    public Object handle(Object input) {
        Integer i = (Integer) input;
        return i * i * i;
    }

}
