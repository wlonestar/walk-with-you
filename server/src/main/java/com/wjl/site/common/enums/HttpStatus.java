package com.wjl.site.common.enums;

/**
 * 返回状态码
 *
 * @author: wjl
 * @date: 2021/11/18 20:06
 * @version: v1.0
 */
public enum HttpStatus {

    OK(200, "success"),
    FAILED(500, "fail"),
    NOT_FOUNT(404, "not found");

    private final int code;
    private final String msg;

    HttpStatus(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
