package tech.stack.hexo.core.constant;

/**
 * @author chenjianyuan
 * @date 2020/8/21 22:49:31
 */
public enum ResultCode {
    /**
     *
     */
    SUCCESS(0, "success");

    private final int code;
    private final String message;

    ResultCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
