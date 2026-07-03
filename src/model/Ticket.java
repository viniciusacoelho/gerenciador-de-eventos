package model;

import enums.Status;
import enums.TicketType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Ticket {

    private static int totalRegisteredTickets = 1;

    private int ticketId;

    private String name;

    private String description;

    private double price;

    private Map<Status, Event> statusEvents;

    private TicketType ticketType;

    public Ticket(String name, String description, double price, TicketType ticketType) {
        this.ticketId = totalRegisteredTickets++;
        this.name = name;
        this.description = description;
        this.price = price;
        this.statusEvents = new HashMap<>();
        this.ticketType = ticketType;
    }

    public Ticket() {
    }

    public static int getTotalRegisteredTickets() {
        return totalRegisteredTickets;
    }

    public int getTicketId() {
        return ticketId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Map<Status, Event> getStatusEvents() {
        return statusEvents;
    }

    public void setStatusEvents(Map<Status, Event> statusEvents) {
        this.statusEvents = statusEvents;
    }

    public TicketType getTicketType() {
        return ticketType;
    }

    public void setTicketType(TicketType ticketType) {
        this.ticketType = ticketType;
    }

    @Override
    public String toString() {
        return "ID: " + ticketId +
                "\nNome: " + name +
                "\nDescrição: " + description +
                "\nPreço: R$ " + price +
                "\nTipo: " + ticketType.getTicketType();
    }

    public abstract double calculatePrice();

}
