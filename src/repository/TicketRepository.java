package repository;

import enums.TicketType;
import model.Event;
import model.Ticket;
import model.TicketHalfPrice;
import model.TicketVip;
import service.TicketService;

import java.util.ArrayList;
import java.util.List;

public class TicketRepository {

    private final List<Ticket> tickets = new ArrayList<>();

    public TicketService ticketService = new TicketService();

    public void createTicket(Ticket ticket) {
        this.tickets.add(ticket);
    }

    public void readTickets() {
        for (Ticket ticket : this.tickets) {
            System.out.println(ticket);

            if (ticket instanceof TicketHalfPrice) {
                System.out.println("Precisa de Carteirinha de Estudante? " + (((TicketHalfPrice) ticket).hasStudentId() ? "Sim" : "Não"));
            } else if (ticket instanceof TicketVip) {
                ticketService.listBenefits(((TicketVip) ticket).getBenefits());
            }

            System.out.println("--------------------------------------------");
        }
    }

    public void readEventTickets(Event event) {
        for (Ticket ticket : event.getTickets()) {
            System.out.println(ticket);

            if (ticket instanceof TicketHalfPrice) {
                System.out.println("Precisa de Carteirinha de Estudante? " + (((TicketHalfPrice) ticket).hasStudentId() ? "Sim" : "Não"));
            } else if (ticket instanceof TicketVip) {
                ticketService.listBenefits(((TicketVip) ticket).getBenefits());
            }

            System.out.println("--------------------------------------------");
        }
    }

    public <T> void updateTicket(Ticket ticket, T attribute, String attributeName) {
        switch (attributeName) {
            case "Nome" -> ticket.setName((String) attribute);
            case "Descrição" -> ticket.setDescription((String) attribute);
            case "Preço" -> ticket.setPrice((double) attribute);
            case "Tipo do ingresso" -> ticket.setPrice(((TicketType) attribute).ordinal());
            case "Carteirinha de Estudante" -> ((TicketHalfPrice) ticket).setStudentId((boolean) attribute);
            case "Benefícios" -> ((TicketVip) ticket).setBenefits((List<String>) attribute);
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

    public Ticket findByEventTicketId(List<Ticket> eventTickets, int ticketId) {
        int start = 0;
        int end = eventTickets.size() - 1;

        while (start <= end) {
            int middle = (start + end) / 2;
            if (eventTickets.get(middle).getTicketId() == ticketId) {
                return eventTickets.get(middle);
            } else if (eventTickets.get(middle).getTicketId() < ticketId) {
                start = middle + 1;
            } else {
                end = middle - 1;
            }
        }

        return null;
    }
}
