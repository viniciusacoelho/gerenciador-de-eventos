package model;

import enums.Status;

import java.util.Map;

public class Ticket {

    private static int totalRegisteredTickets = 1;

    private int ticketId;

    private String name;

    private String description;

    private double price;

    private Status status;
    // TODO:
//    private Map<Status, Event> statusEvents;

    public Ticket(String name, String description, double price) {
        this.ticketId = totalRegisteredTickets++;
        this.name = name;
        this.description = description;
        this.price = price;
        this.status = Status.AVAILABLE;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ID: " + ticketId +
                "\nNome: " + name +
                "\nDescrição: " + description +
                "\nPreço: R$ " + price;
    }

}
