package tech.stack.hexo.core.exception;

/**
 * @author chenjianyuan
 * @date 2020/8/22 12:17:09
 */
public class NoSuchResException extends AdminException {
    public NoSuchResException() {
        super("resource not exist.");
    }

    public NoSuchResException(String res) {
        super(String.format("资源 [%s] 不存在", res));
    }
}
