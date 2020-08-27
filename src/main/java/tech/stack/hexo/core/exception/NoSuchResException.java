package tech.stack.hexo.core.exception;

/**
 * @author chenjianyuan
 * @date 2020/8/22 12:17:09
 */
public class NoSuchResException extends AdminException {
    public NoSuchResException() {
        super("resource not exist.");
    }

    public NoSuchResException(String message) {
        super(message);
    }
}
