package exceptions;

/**
 * Exceção lançada quando um ingresso não é encontrado.
 */
public class TicketNotFoundException extends RuntimeException {

    public TicketNotFoundException() {
        super("Ingresso não encontrado.");
    }

}
