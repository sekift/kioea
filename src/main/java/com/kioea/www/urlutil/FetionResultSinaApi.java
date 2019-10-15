package com.kioea.www.urlutil;

/**
 * 
 * @author:sekift
 * @time:2015-2-13 下午04:04:36
 * @version:
 */
public class FetionResultSinaApi {
    private boolean ifSucceed;
    private String result;
     
    public FetionResultSinaApi() { }
     
    public FetionResultSinaApi(boolean ifSucceed, String result) {
        this.ifSucceed = ifSucceed;
        this.result = result;
    }
     
    public boolean isIfSucceed() {
        return ifSucceed;
    }
    public void setIfSucceed(boolean ifSucceed) {
        this.ifSucceed = ifSucceed;
    }
    public String getResult() {
        return result;
    }
    public void setResult(String result) {
        this.result = result;
    }
     
}
