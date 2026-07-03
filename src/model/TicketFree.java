package model;

import enums.TicketType;

public class TicketFree extends Ticket {

    public TicketFree(String name, String description, double price, TicketType ticketType) {
        super(name, description, price, ticketType);
    }

    public TicketFree() {
    }

    @Override
    public double calculatePrice() {
        return 0;
    }

}
