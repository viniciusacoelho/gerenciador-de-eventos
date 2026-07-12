package model;

import enums.TicketType;

public class TicketHalfPrice extends Ticket {

    private boolean studentId;

    public TicketHalfPrice(String name, String description, double price, TicketType ticketType, boolean studentId) {
        super(name, description, price, ticketType);
        this.studentId = studentId;
    }

    public TicketHalfPrice() {
    }

    public boolean hasStudentId() {
        return studentId;
    }

    public void setStudentId(boolean studentId) {
        this.studentId = studentId;
    }

    @Override
    public double calculatePrice() {
        return hasStudentId() ? getPrice() / 2 : -1;
    }

    @Override
    public String toString() {
        return "ID: " + super.getTicketId() +
                "\nNome: " + super.getName() +
                "\nDescrição: " + super.getDescription() +
                "\nPreço: R$ " + super.getPrice();
    }

}