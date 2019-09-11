package by.mygroup.exception;

public class CubeException extends Exception {
    public CubeException(String msg) {
        super(msg);
    }

    public CubeException(String message, Throwable cause) {
        super(message, cause);
    }

    public CubeException(Throwable cause) {
        super(cause);
    }

    public CubeException() {
    }
}
