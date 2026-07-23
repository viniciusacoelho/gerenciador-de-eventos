package exceptions;

/**
 * Exceção lançada quando um participante não é encontrado em um determinado evento.
 */
public class ParticipantEventNotFoundException extends RuntimeException {

    public ParticipantEventNotFoundException(String message) {
        super(message);
    }

}
