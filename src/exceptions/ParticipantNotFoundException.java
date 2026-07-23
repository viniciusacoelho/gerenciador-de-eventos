package exceptions;

/**
 * Exceção lançada quando um participante não é encontrado.
 */
public class ParticipantNotFoundException extends RuntimeException {

    public ParticipantNotFoundException(String message) {
        super(message);
    }

}
