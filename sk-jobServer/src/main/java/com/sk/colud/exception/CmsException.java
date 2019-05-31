package com.sk.colud.exception;

public class CmsException extends RuntimeException {
    public CmsException(String msg)
    {
        super(msg);
    }

    public CmsException(Throwable t) {
        super(t);
    }

    public CmsException(String msg, Throwable t) {
        super(msg, t);
    }
}
