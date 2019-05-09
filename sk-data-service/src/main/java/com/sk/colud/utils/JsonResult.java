package com.sk.colud.utils;
/**
* 2019年3月12日 上午11:41:29
* @HXing xu
* sk-redis
* 
**/
import java.io.Serializable;

public class JsonResult implements Serializable{ 

     private static final long serialVersionUID = 0xb46245f34361521L;
        private Integer code;
        private Object data;
        private String message="";
        public static final int ERROR = 1;
        public static final int SUCCESS = 0;
        private Integer count;

    public JsonResult()
    {
    }
    public JsonResult(Integer code)
    {
        this.code = Integer.valueOf(code);
    }

    public JsonResult(Integer code, Object data, String message, Integer count)
    {
        this.code = SUCCESS;
        this.data = data;
        this.count = count;
        this.message = message;
    }

    public JsonResult(Throwable e)
    {
        code = ERROR;
        data = null;
        message = e.getMessage();
    }

    public JsonResult(Object data, Integer count)
    {
        code = SUCCESS;
        this.data = data;
        this.count = count;
        message = "";
    }

    public JsonResult(Object data ,String message )
    {
        code = SUCCESS;
        this.data = data;
        this.message = message;
    }
    public JsonResult(String errorMsg)
    {
        this.code = ERROR;
        data = null;
        message = errorMsg;
    }
    public Integer getcode()
    {
        return code;
    }

    public void setcode(Integer code)
    {
        this.code = code;
    }

    public Object getData()
    {
        return data;
    }

    public void setData(Object data)
    {
        this.data = data;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public Integer getCount()
    {
        return count;
    }

    public void setCount(Integer count)
    {
        this.count = count;
    }

    public String toString()
    {
        return (new StringBuilder("JsonResult [code=")).append(code).append(", data=").append(data).append(", message=").append(message).append("]").toString();
    }


}
