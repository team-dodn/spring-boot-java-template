package io.dodn.springboot.core.api.config;

import io.dodn.springboot.core.support.error.CoreException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;

import java.lang.reflect.Method;

public class AsyncExceptionHandler implements AsyncUncaughtExceptionHandler {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Override
    public void handleUncaughtException(Throwable e, Method method, Object... params) {
        if (e instanceof CoreException) {
            switch (((CoreException) e).getErrorType().getLogLevel()) {
                case ERROR -> log.error("CoreException : {}", e.getMessage(), e);
                case WARN -> log.warn("CoreException : {}", e.getMessage(), e);
                default -> log.info("CoreException : {}", e.getMessage(), e);
            }
        }
        else {
            log.error("Exception : {}", e.getMessage(), e);
        }
    }

}
