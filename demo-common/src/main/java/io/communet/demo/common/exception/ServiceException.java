package io.communet.demo.common.exception;

/**
 * <p>function:自定义业务异常
 * <p>User: leejohn
 * <p>Date: 16/5/10
 * <p>Version: 1.0
 */
public class ServiceException extends RuntimeException {
    private static final long serialVersionUID = 7479394131878985931L;

    /**
     *
     */
    public ServiceException() {
        super("no message");
    }

    /**
     * @param message
     */
    public ServiceException(String message) {
        super(message);
    }

    /**
     * @param cause
     */
    public ServiceException(Throwable cause) {
        super(cause);
    }

    /**
     * @param message
     * @param cause
     */
    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}