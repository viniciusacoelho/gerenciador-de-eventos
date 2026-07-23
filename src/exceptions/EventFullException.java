package exceptions;

/**
 * Exceção lançada quando um participante tenta se inscrever em um evento que já atingiu sua capacidade máxima.
 */
public class EventFullException extends RuntimeException {

    public EventFullException(String message) {
        super(message);
    }

}
