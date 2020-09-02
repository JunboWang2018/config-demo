package com.demo.config.www.common.exception.cache;

import cn.com.scooper.common.resp.ResultCode;

/**
 * @Description 缓存不可用异常
 * @author Wang Junbo
 * @create 2020-06-15 16:21
 */
public class CacheUnusableException extends Exception {
    private static final long serialVersionUID = 8433836259545944091L;

    /**
     * 返回状态码
     */
    private int code = ResultCode.FAIL;

    public CacheUnusableException(){
        super();
    }

    /**
     * CacheUnusableException
     * @param message
     */
    public CacheUnusableException(String message){
        super(message);
    }

    /**
     * CacheUnusableException
     * @param code
     * @param message
     */
    public CacheUnusableException(String message, int code){
        super(message);
        this.code = code;
    }

    /**
     * CacheUnusableException
     * @param message
     * @param cause
     */
    public CacheUnusableException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * CacheUnusableException
     * @param message
     * @param code
     * @param cause
     */
    public CacheUnusableException(String message, int code, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
