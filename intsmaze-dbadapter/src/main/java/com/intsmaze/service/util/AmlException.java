package com.intsmaze.service.util;
/** 
 * @author:YangLiu
 * @date:2017年12月20日 下午7:45:13 
 * @describe: 
 */
public class AmlException extends RuntimeException {

    private final static long serialVersionUID = 1L;

    public AmlException(String message, Throwable cause) {
        super(message, cause);
    }

    public AmlException(String message) {
        super(message);
    }

    public AmlException(Exception e) {
        super(e);
    }
    
    public AmlException(Throwable cause) {
        super(cause);
    }

    public AmlException() {
        super();
    }

    
    
}

