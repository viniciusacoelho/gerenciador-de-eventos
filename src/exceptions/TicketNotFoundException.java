package exceptions;

public class TicketNotFoundException extends RuntimeException {

    public TicketNotFoundException() {
        super("Ingresso não encontrado.");
    }

}
