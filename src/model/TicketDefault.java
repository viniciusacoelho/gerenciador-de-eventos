package model;

import enums.TicketType;

public class TicketDefault extends Ticket {

    public TicketDefault(String name, String description, double price, TicketType ticketType) {
        super(name, description, price, ticketType);
    }

    public TicketDefault() {
    }

    @Override
    public double calculatePrice() {
        return getPrice();
    }

}
