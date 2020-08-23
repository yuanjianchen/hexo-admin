package tech.stack.hexo.core.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import tech.stack.hexo.core.exception.AdminException;
import tech.stack.hexo.model.Result;

/**
 * @author chenjianyuan
 * @date 2020/8/22 12:47:14
 */
@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({AdminException.class})
    public ResponseEntity<Result<Void>> adminExceptionHandler(AdminException adminException) {
        StackTraceElement stackTraceElement = adminException.getStackTrace()[0];
        String className = stackTraceElement.getClassName();
        String methodName = stackTraceElement.getMethodName();
        log.warn("admin exception occurred. {}.{} - {}", className, methodName, adminException.getMessage());
        return ResponseEntity.ok(Result.err(adminException.getMessage()));
    }

}
