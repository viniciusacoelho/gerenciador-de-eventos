package exceptions;

public class IncorrectDefaultPasswordException extends RuntimeException {

    public IncorrectDefaultPasswordException(String message) {
        super(message);
    }

}
