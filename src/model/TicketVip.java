package model;

import enums.TicketType;
import service.TicketService;

import java.util.ArrayList;
import java.util.List;

public class TicketVip extends Ticket {

    private List<String> benefits = new ArrayList<>();

    private final TicketService ticketService = new TicketService();

    public TicketVip(String name, String description, double price, TicketType ticketType, List<String> benefits) {
        super(name, description, price, ticketType);
        this.benefits = benefits;
    }

    public TicketVip() {
    }

    public List<String> getBenefits() {
        return benefits;
    }

    public void setBenefits(List<String> benefits) {
        this.benefits = benefits;
    }

    @Override
    public double calculatePrice() {
//        return getPrice() * 1.5;
        return getPrice() * 2;
    }

    @Override
    public String toString() {
        return "ID: " + super.getTicketId() +
                "\nNome: " + super.getName() +
                "\nDescrição: " + super.getDescription() +
                "\nPreço: R$ " + super.getPrice() +
                "\nBenefícios:";
    }

}
