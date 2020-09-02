package com.demo.config.www.common.exception.cache;

import cn.com.scooper.common.resp.ResultCode;

/**
 * @Description 缓存已存在异常
 * @author Wang Junbo
 * @create 2020-06-15 16:21
 */
public class CacheExistingException extends Exception {
    private static final long serialVersionUID = 8433836259545944091L;

    /**
     * 返回状态码
     */
    private int code = ResultCode.FAIL;

    public CacheExistingException(){
        super();
    }

    /**
     * CacheExistingException
     * @param message
     */
    public CacheExistingException(String message){
        super(message);
    }

    /**
     * CacheExistingException
     * @param code
     * @param message
     */
    public CacheExistingException(String message, int code){
        super(message);
        this.code = code;
    }

    /**
     * CacheExistingException
     * @param message
     * @param cause
     */
    public CacheExistingException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * CacheExistingException
     * @param message
     * @param code
     * @param cause
     */
    public CacheExistingException(String message, int code, Throwable cause) {
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
