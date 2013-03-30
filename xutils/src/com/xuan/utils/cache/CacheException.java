package com.xuan.utils.cache;

/**
 * 表示操作缓存发生异常的异常类
 * 
 * @author xuan
 * @version $Revision: 1.0 $, $Date: 2012-11-22 上午10:39:10 $
 */
public class CacheException extends RuntimeException {

    private static final long serialVersionUID = 7658954499327650558L;

    public CacheException() {
        super();
    }

    public CacheException(String message, Throwable cause) {
        super(message, cause);
    }

    public CacheException(String message) {
        super(message);
    }

    public CacheException(Throwable cause) {
        super(cause);
    }

}
