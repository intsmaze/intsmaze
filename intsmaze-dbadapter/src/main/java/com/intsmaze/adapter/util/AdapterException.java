package com.intsmaze.adapter.util;
/** 
 * @author:YangLiu
 * @date:2017年12月20日 下午7:45:13 
 * @describe: 
 */
public class AdapterException extends RuntimeException {

    private final static long serialVersionUID = 1L;

    public AdapterException(String message, Throwable cause) {
        super(message, cause);
    }

    public AdapterException(String message) {
        super(message);
    }

    public AdapterException(Throwable cause) {
        super(cause);
    }

    public AdapterException() {
        super();
    }

}

