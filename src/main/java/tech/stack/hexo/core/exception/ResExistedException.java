package tech.stack.hexo.core.exception;

/**
 * @author chenjianyuan
 * @date 2020/8/22 12:28:08
 */
public class ResExistedException extends AdminException{
    public ResExistedException() {
        super("resource has existed.");
    }

    public ResExistedException(String message) {
        super(message);
    }
}
