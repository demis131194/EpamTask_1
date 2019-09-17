package by.epamgroup.cube.exception;

public class CubeParseException extends Exception {
    public CubeParseException(String msg) {
        super(msg);
    }

    public CubeParseException() {
        super();
    }

    public CubeParseException(String message, Throwable cause) {
        super(message, cause);
    }

    public CubeParseException(Throwable cause) {
        super(cause);
    }
}
