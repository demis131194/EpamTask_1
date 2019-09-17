package by.epamgroup.cube.exception;

import java.io.IOException;

public class CubeReaderException extends Exception {

    public CubeReaderException() {
        super();
    }

    public CubeReaderException(String message) {
        super(message);
    }

    public CubeReaderException(String message, Throwable cause) {
        super(message, cause);
    }

    public CubeReaderException(Throwable cause) {
        super(cause);
    }
}
