package tech.stack.hexo.model;

import lombok.Data;
import tech.stack.hexo.core.constant.ResultCode;

/**
 * @author chenjianyuan
 * @date 2020/8/21 22:44:24
 */
@Data
public class Result<T> {
    private int code;
    private String message;
    private T data;

    private Result() {

    }

    private Result(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public Result(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    private Result(ResultCode code) {
        this.code = code.getCode();
        this.message = code.getMessage();
    }

    public static Result<Void> ok() {
        Result<Void> result = new Result<>();
        result.code = 0;
        result.message = "success";
        return result;
    }

    public static <T> Result<T> ok(T data) {
        Result<T> result = new Result<>();
        result.code = 0;
        result.message = "success";
        result.data = data;
        return result;
    }

    public static Result<Void> err() {
        Result<Void> result = new Result<>();
        result.code = -1;
        result.message = "error";
        return result;
    }

    public static Result<Void> err(String message) {
        Result<Void> result = new Result<>();
        result.code = -1;
        result.message = message;
        return result;
    }

    public boolean getSuccess() {
        return this.code >= 0;
    }
}
