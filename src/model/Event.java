package model;

import util.DateTimeUtil;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Representa um evento que pode ser gerenciado pelo sistema, contendo informações
 * como nome, descrição, data, local, capacidade, participantes e ingressos.
 */
public class Event {

    private static int totalRegisteredEvents = 1;

    private int eventId;

    private String name;

    private LocalDateTime dateTime;

    private String location;

    private int capacity;

    private List<Participant> participants;

    private List<Ticket> tickets;

    private static final DateTimeUtil dateTimeUtil = new DateTimeUtil();

    /**
     * Cria um novo evento com as informações fornecidas.
     *
     * @param name nome do evento.
     * @param dateTime data e horário de realização do evento.
     * @param location local onde o evento será realizado.
     * @param capacity quantidade máxima de participantes permitidos no evento.
     */
    public Event(String name, LocalDateTime dateTime, String location, int capacity) {
        this.eventId = totalRegisteredEvents++;
        this.name = name;
        this.dateTime = dateTime;
        this.location = location;
        this.capacity = capacity;
        this.participants = new ArrayList<>();
        this.tickets = new ArrayList<>();
    }

    /**
     * Cria um evento sem inicializar seus atributos.
     */
    public Event() {
    }

    /**
     * Retorna a quantidade total de participantes cadastrados no evento.
     *
     * @return quantidade total de participantes cadastrados.
     */
    public int getTotalRegisteredEvents() {
        return totalRegisteredEvents;
    }

    /**
     * Retorna o identificador do evento.
     *
     * @return identificador do evento.
     */
    public int getEventId() {
        return eventId;
    }

    /**
     * Retorna o nome do evento.
     *
     * @return nome do evento.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Define o nome do evento.
     *
     * @param name novo nome do evento.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retorna a data e o horário de realização do evento.
     *
     * @return data e horário do evento.
     */
    public LocalDateTime getDateTime() {
        return this.dateTime;
    }

    /**
     * Define a data e o horário de realização do evento.
     *
     * @param dateTime nova data e horário do evento.
     */
    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    /**
     * Retorna o local de realização do evento.
     *
     * @return local do evento.
     */
    public String getLocation() {
        return this.location;
    }

    /**
     * Define o local de realização do evento.
     *
     * @param location novo local do evento.
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Retorna a capacidade máxima de participantes do evento.
     *
     * @return capacidade máxima do evento.
     */
    public int getCapacity() {
        return this.capacity;
    }

    /**
     * Define a capacidade máxima de participantes do evento.
     *
     * @param capacity nova capacidade máxima do evento.
     */
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    /**
     * Retorna a lista de participantes cadastrados no evento.
     *
     * @return lista de participantes do evento.
     */
    public List<Participant> getParticipants() {
        return participants;
    }

    /**
     * Adiciona um participante à lista de participantes do evento.
     *
     * @param participant participante que será adicionado ao evento.
     */
    public void setParticipants(Participant participant) {
        this.participants.add(participant);
    }

    /**
     * Retorna a lista de ingressos disponíveis para o evento.
     *
     * @return lista de ingressos do evento.
     */
    public List<Ticket> getTickets() {
        return tickets;
    }

    /**
     * Adiciona um ingresso à lista de ingressos do evento.
     *
     * @param ticket ingresso que será adicionado ao evento.
     */
    public void setTickets(Ticket ticket) {
        this.tickets.add(ticket);
    }

    /**
     * Retorna uma representação textual das informações do evento.
     *
     * @return representação em formato de texto do evento.
     */
    @Override
    public String toString() {
        return "ID: " + eventId +
                "\nNome: " + name +
                "\nHorário: " + dateTimeUtil.formatDateTime(dateTime) +
                "\nLocal: " + location +
                "\nCapacidade: " + capacity +
                "\nNúmero de participantes confirmados: " + confirmedParticipants();
    }

    /**
     * Retorna a quantidade de participantes com presença confirmada no evento.
     *
     * @return quantidade de participantes com presença confirmada.
     */
    public int confirmedParticipants() {
        return participants.toArray().length;
    }

}
