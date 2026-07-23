package exceptions;

/**
 * Exceção lançada quando um evento não é encontrado.
 */
public class EventNotFoundException extends RuntimeException {

    public EventNotFoundException(String message) {
        super(message);
    }

}
