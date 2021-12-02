package com.wjl.site.common.constants;

import com.wjl.site.common.enums.HttpStatus;
import lombok.Data;

/**
 * 统一返回格式
 *
 * @author: wjl
 * @date: 2021/11/18 20:06
 * @version: v1.0
 */
@Data
public class Result<T> {

    private int status;
    private String msg;
    private T data;

    public static <T> Result<T> success(T data) {
        Result<T> Result = new Result<>();
        Result.setStatus(HttpStatus.OK.getCode());
        Result.setMsg(HttpStatus.OK.getMsg());
        Result.setData(data);
        return Result;
    }

    public static <T> Result<T> success(String msg) {
        Result<T> Result = new Result<>();
        Result.setStatus(HttpStatus.OK.getCode());
        Result.setMsg(msg);
        return Result;
    }

    public static <T> Result<T> fail(int status, String msg) {
        Result<T> Result = new Result<>();
        Result.setStatus(status);
        Result.setMsg(msg);
        return Result;
    }

}
