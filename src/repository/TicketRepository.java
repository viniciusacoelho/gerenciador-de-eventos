package repository;

import enums.TicketType;
import model.Event;
import model.Ticket;
import model.TicketHalfPrice;
import model.TicketVip;
import service.TicketService;

import java.util.ArrayList;
import java.util.List;

/**
 * Responsável por armazenar e gerenciar os ingressos cadastrados no sistema.
 */
public class TicketRepository {

    private final List<Ticket> tickets = new ArrayList<>();

    public TicketService ticketService = new TicketService();

    /**
     * Adiciona um novo ingresso ao repositório.
     *
     * @param ticket ticket ingresso que será cadastrado.
     */
    public void createTicket(Ticket ticket) {
        this.tickets.add(ticket);
    }

    /**
     * Exibe todos os ingressos cadastrados no sistema.
     */
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

    /**
     * Exibe os ingressos disponíveis de um determinado evento.
     *
     * @param event evento cujos ingressos serão exibidos.
     */
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

    /**
     * Atualiza um atributo específico de um ingresso.
     *
     * @param ticket ingresso que será atualizado.
     * @param attribute novo valor do atributo.
     * @param attributeName nome do atributo que será atualizado.
     * @param <T> tipo do atributo informado.
     */
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

    /**
     * Remove um ingresso do repositório.
     *
     * @param ticket ingresso que será removido.
     */
    public void deleteTicket(Ticket ticket) {
        this.tickets.remove(ticket);
    }

    /**
     * Busca um ingresso pelo seu identificador.
     *
     * @param ticketId identificador do ingresso.
     * @return ingresso correspondente ao identificador informado.
     */
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

    /**
     * Busca um ingresso pelo identificador dentro da lista de ingressos de um evento.
     *
     * @param eventTickets lista de ingressos do evento.
     * @param ticketId identificador do ingresso.
     * @return ingresso correspondente ao identificador informado.
     */
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
