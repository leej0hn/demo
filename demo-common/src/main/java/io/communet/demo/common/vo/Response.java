package io.communet.demo.common.vo;

import com.google.common.base.MoreObjects;

import java.io.Serializable;

/**
 * <p>function:
 * <p>User: LeeJohn
 * <p>Date: 2017/3/15
 * <p>Version: 1.0
 */
public class Response<T> implements Serializable {
    private static final long serialVersionUID = -750644833749014618L;
    private boolean success;
    private T result;
    private String error;

    public Response() {
    }

    public boolean isSuccess() {
        return this.success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getResult() {
        return this.result;
    }

    public void setResult(T result) {
        this.success = true;
        this.result = result;
    }

    public String getError() {
        return this.error;
    }

    public void setError(String error) {
        this.success = false;
        this.error = error;
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("success", this.success).add("result", this.result).add("error", this.error).omitNullValues().toString();
    }

    public static <T> Response<T> ok(T data) {
        Response resp = new Response();
        resp.setResult(data);
        return resp;
    }

    public static <T> Response<T> ok() {
        return ok(null);
    }

    public static <T> Response<T> fail(String error) {
        Response resp = new Response();
        resp.setError(error);
        return resp;
    }
}
