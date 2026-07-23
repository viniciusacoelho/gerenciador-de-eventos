package model;

import enums.Status;
import enums.TicketType;

import java.util.HashMap;
import java.util.Map;

/**
 * Representa um ingresso abstrato de um evento, contendo informações comuns
 * como nome, descrição, preço e tipo de ingresso.
 */
public abstract class Ticket {

    private static int totalRegisteredTickets = 1;

    private int ticketId;

    private String name;

    private String description;

    private double price;

    private Map<Status, Event> statusEvents;

    private Status status;

    private TicketType ticketType;

    /**
     * Cria um novo ingresso com as informações fornecidas.
     *
     * @param name nome do ingresso.
     * @param description descrição do ingresso.
     * @param price preço do ingresso.
     * @param ticketType tipo do ingresso.
     */
    public Ticket(String name, String description, double price, TicketType ticketType) {
        this.ticketId = totalRegisteredTickets++;
        this.name = name;
        this.description = description;
        this.price = price;
        this.statusEvents = new HashMap<>();
        this.status = Status.AVAILABLE;
        this.ticketType = ticketType;
    }

    /**
     * Cria um ingresso sem inicializar seus atributos.
     */
    public Ticket() {
    }

    /**
     * Retorna a quantidade total de ingressos cadastrados no sistema.
     *
     * @return quantidade total de ingressos cadastrados.
     */
    public int getTotalRegisteredTickets() {
        return totalRegisteredTickets;
    }

    /**
     * Retorna o identificador do ingresso.
     *
     * @return identificador do ingresso.
     */
    public int getTicketId() {
        return ticketId;
    }

    /**
     * Retorna o nome do ingresso.
     *
     * @return nome do ingresso.
     */
    public String getName() {
        return name;
    }

    /**
     * Define o nome do ingresso.
     *
     * @param name novo nome do ingresso.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retorna a descrição do ingresso.
     *
     * @return descrição do ingresso.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Define a descrição do ingresso.
     *
     * @param description description nova descrição do ingresso.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Retorna o preço do ingresso.
     *
     * @return preço do ingresso.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Define o preço do ingresso.
     *
     * @param price novo preço do ingresso.
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Retorna o mapa contendo os eventos associados aos seus respectivos status.
     *
     * @return mapa de status associados aos eventos.
     */
    public Map<Status, Event> getStatusEvents() {
        return statusEvents;
    }

    /**
     * Associa um evento a um determinado status.
     *
     * @param status status que será atribuído ao evento.
     * @param event evento associado ao status informado.
     */
    public void setStatusEvents(Status status, Event event) {
        this.statusEvents.put(status, event);
    }

    /**
     * Retorna o status atual do ingresso.
     *
     * @return status do ingresso.
     */
    public Status getStatus() {
        return status;
    }

    /**
     * Define o status do ingresso.
     *
     * @param status status novo status do ingresso.
     */
    public void setStatus(Status status) {
        this.status = status;
    }

    /**
     * Retorna o tipo do ingresso.
     *
     * @return tipo do ingresso.
     */
    public TicketType getTicketType() {
        return ticketType;
    }

    /**
     * Define o tipo do ingresso.
     *
     * @param ticketType novo tipo do ingresso.
     */
    public void setTicketType(TicketType ticketType) {
        this.ticketType = ticketType;
    }

    /**
     * Retorna uma representação textual das informações do ingresso.
     *
     * @return representação em formato de texto do ingresso.
     */
    @Override
    public String toString() {
        return "ID: " + ticketId +
                "\nNome: " + name +
                "\nDescrição: " + description +
                "\nPreço: R$ " + price +
                "\nTipo: " + ticketType.getTicketType();
    }

    /**
     * Calcula o preço final do ingresso de acordo com suas regras específicas.
     *
     * @return preço calculado do ingresso.
     */
    public abstract double calculatePrice();

}
