package repository;

import model.Ticket;

import java.util.ArrayList;
import java.util.List;

public class TicketRepository {

    private final List<Ticket> tickets = new ArrayList<>();

    public void createTicket(Ticket ticket) {
        this.tickets.add(ticket);
    }

    public void readTickets() {
        for (Ticket ticket : this.tickets) {
            System.out.println(ticket);
        }
    }

    public <T> void updateTicket(String attributeName, Ticket ticket, T attribute) {
        switch (attributeName) {
            case "Nome" -> ticket.setName((String) attribute);
            case "Descrição" -> ticket.setDescription((String) attribute);
            case "Preço" -> ticket.setPrice((double) attribute);
            default -> System.out.println("Atributo inválido! Tente novamente.");
        }

        System.out.println(attributeName + " atualizado com sucesso!");
    }

    public void deleteTicket(Ticket ticket) {
        this.tickets.remove(ticket);
    }

    public Ticket findTicketById(int ticketId) {
        int start = 0;
        int end = this.tickets.size() - 1;

        while (start <= end) {
            int middle = (start + end) / 2;
            if (this.tickets.get(middle).getTicketId() == ticketId) {
                return this.tickets.get(middle);
            } else if (this.tickets.get(middle).getTicketId() < ticketId) {
                start = middle + 1;
            } else {
                end = middle - 1;
            }
        }

        return null;
    }

}
