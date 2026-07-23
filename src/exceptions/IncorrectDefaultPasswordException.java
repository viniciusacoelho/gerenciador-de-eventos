package exceptions;

/**
 * Exceção lançada quando a senha padrão informada está incorreta.
 */
public class IncorrectDefaultPasswordException extends RuntimeException {

    public IncorrectDefaultPasswordException(String message) {
        super(message);
    }

}
