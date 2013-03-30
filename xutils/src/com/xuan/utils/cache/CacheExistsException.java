package com.xuan.utils.cache;

/**
 * 表示缓存已经存在的异常类
 * 
 * @author xuan
 * @version $Revision: 1.0 $, $Date: 2012-11-22 上午10:39:34 $
 */
public class CacheExistsException extends CacheException {

    private static final long serialVersionUID = 6249341816801412201L;

    public CacheExistsException() {
        super();
    }

    public CacheExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public CacheExistsException(String message) {
        super(message);
    }

    public CacheExistsException(Throwable cause) {
        super(cause);
    }

}
