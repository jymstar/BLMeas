package com.example.blmeas.common;

public class Result<T> {
    private int code;       // 0=成功，非0=失败
    private String msg;     // 提示信息
    private T data;         // 业务数据

    // 成功（带数据）
    public static <T> Result<T> ok(T data) {
        Result<T> r = new Result<>();
        r.code = 0;
        r.msg = "success";
        r.data = data;
        return r;
    }

    // 成功（不带数据）
    public static <T> Result<T> ok() {
        return ok(null);
    }

    // 失败
    public static <T> Result<T> fail(String msg) {
        Result<T> r = new Result<>();
        r.code = 1;
        r.msg = msg;
        return r;
    }

    // Getter & Setter
    public int getCode() { return code; }
    public void setCode(int code) { this.code = code; }
    public String getMsg() { return msg; }
    public void setMsg(String msg) { this.msg = msg; }
    public T getData() { return data; }
    public void setData(T data) { this.data = data; }
}