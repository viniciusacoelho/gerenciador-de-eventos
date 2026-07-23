package enums;

/**
 * Representa os tipos de ingressos disponíveis para um evento.
 */
public enum TicketType {

    TICKET_DEFAULT ("Ingresso Padrão"),
    TICKET_FREE ("Ingresso Grátis"),
    TICKET_HALF_PRICE ("Ingresso Meia-Entrada"),
    TICKET_VIP ("Ingresso Vip");

    private final String ticketType;

    TicketType(String ticketType) {
        this.ticketType = ticketType;
    }

    public String getTicketType() {
        return ticketType;
    }

}
