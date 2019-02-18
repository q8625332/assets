package com.ljq.assets.util;

/**
 * @email 867170960@qq.com
 * @author:刘俊秦
 * @date: 2018/9/16 0016
 * @time: 下午 3:04
 */
public class RuntimeError extends RuntimeException{

    public RuntimeError() {
        super();
    }

    public RuntimeError(String message) {
        super(message);
    }

    public RuntimeError(String message, Throwable cause) {
        super(message, cause);
    }

    public RuntimeError(Throwable cause) {
        super(cause);
    }

    protected RuntimeError(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
