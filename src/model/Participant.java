package model;

import enums.Attendance;
import util.DateTimeUtil;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Representa um participante do sistema, contendo suas informações pessoais,
 * credenciais de acesso, eventos inscritos e histórico de participação.
 */
public class Participant {

    public static int totalRegisteredParticipants = 1;

    private int participantId;

    private String name;

    private String contact;

    private String email;

    private String password;

    private LocalDateTime accountDateTimeCreation;

    private List<Event> events;

    private Map<Event, Attendance> attendanceEvents;

    private Map<Event, Ticket> eventTickets;

    private final static DateTimeUtil dateTimeUtil = new DateTimeUtil();

    /**
     * Cria um novo participante com as informações fornecidas.
     *
     * @param name nome do participante.
     * @param contact contato do participante.
     * @param email endereço de e-mail do participante.
     * @param password senha de acesso do participante.
     */
    public Participant(String name, String contact, String email, String password) {
        this.participantId = totalRegisteredParticipants++;
        this.name = name;
        this.contact = contact;
        this.email = email;
        this.password = password;
        this.accountDateTimeCreation = LocalDateTime.now();
        this.events = new ArrayList<>();
        this.attendanceEvents = new HashMap<>();
        this.eventTickets = new HashMap<>();
    }

    /**
     * Cria um participante sem inicializar seus atributos.
     */
    public Participant() {
    }

    /**
     * Retorna a quantidade total de participantes cadastrados no sistema.
     *
     * @return quantidade total de participantes cadastrados.
     */
    public int getTotalRegisteredParticipants() {
        return totalRegisteredParticipants;
    }

    /**
     * Retorna o identificador do participante.
     *
     * @return identificador do participante.
     */
    public int getParticipantId() {
        return participantId;
    }

    /**
     * Retorna o nome do participante.
     *
     * @return nome do participante.
     */
    public String getName() {
        return name;
    }

    /**
     * Define o nome do participante.
     *
     * @param name novo nome do participante.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retorna o contato do participante.
     *
     * @return contato do participante.
     */
    public String getContact() {
        return contact;
    }

    /**
     * Define o contato do participante.
     *
     * @param contact novo contato do participante.
     */
    public void setContact(String contact) {
        this.contact = contact;
    }

    /**
     * Retorna o endereço de e-mail do participante.
     *
     * @return endereço de e-mail do participante.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Define o endereço de e-mail do participante.
     *
     * @param email novo endereço de e-mail do participante.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Retorna a senha do participante.
     *
     * @return senha do participante.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Define a senha do participante.
     *
     * @param password nova senha do participante.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Retorna a lista de eventos nos quais o participante está inscrito.
     *
     * @return lista de eventos do participante.
     */
    public List<Event> getEvents() {
        return events;
    }

    /**
     * Adiciona um evento à lista de eventos do participante.
     *
     * @param event evento que será adicionado ao participante.
     */
    public void setEvents(Event event) {
        this.events.add(event);
        setAttendanceEvents(event, Attendance.PENDING);
    }

    /**
     * Retorna o mapa contendo os eventos e seus respectivos status de presença do participante.
     *
     * @return mapa de eventos associados ao status de presença.
     */
    public Map<Event, Attendance> getAttendanceEvents() {
        return attendanceEvents;
    }

    /**
     * Define o status de presença do participante em um determinado evento.
     *
     * @param event evento ao qual o status de presença será associado.
     * @param attendance status de presença do participante no evento.
     */
    public void setAttendanceEvents(Event event, Attendance attendance) {
        this.attendanceEvents.put(event, attendance);
    }

    /**
     * Retorna o mapa contendo os eventos e os respectivos ingressos adquiridos pelo participante.
     *
     * @return mapa de eventos associados aos ingressos do participante.
     */
    public Map<Event, Ticket> getEventTickets() {
        return eventTickets;
    }

    /**
     * Associa um ingresso adquirido pelo participante a um determinado evento.
     *
     * @param event evento ao qual o ingresso pertence.
     * @param ticket ingresso adquirido pelo participante.
     */
    public void setEventTickets(Event event, Ticket ticket) {
        this.eventTickets.put(event, ticket);
    }

    /**
     * Retorna a data e o horário de criação da conta do participante.
     *
     * @return data e horário de criação da conta.
     */
    public LocalDateTime getAccountDateTimeCreation() {
        return accountDateTimeCreation;
    }

    /**
     * Compara este participante com outro objeto para verificar se são equivalentes.
     *
     * @param o objeto que será comparado com este participante.
     * @return true se os objetos forem iguais; caso contrário, false.
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Participant that = (Participant) o;
        return participantId == that.participantId && Objects.equals(name, that.name) && Objects.equals(contact, that.contact) && Objects.equals(email, that.email) && Objects.equals(password, that.password) && Objects.equals(accountDateTimeCreation, that.accountDateTimeCreation) && Objects.equals(events, that.events) && Objects.equals(attendanceEvents, that.attendanceEvents) && Objects.equals(eventTickets, that.eventTickets);
    }

    /**
     * Retorna o código hash do participante, utilizado para identificar o objeto
     * em estruturas de dados baseadas em hash.
     *
     * @return código hash do participante.
     */
    @Override
    public int hashCode() {
        return Objects.hash(participantId, name, contact, email, password, accountDateTimeCreation, events, attendanceEvents, eventTickets);
    }

    /**
     * Retorna uma representação textual das informações do participante.
     *
     * @return representação em formato de texto do participante.
     */
    @Override
    public String toString() {
        return "ID: " + participantId +
                "\nNome: " + name +
                "\nContato: " + contact +
                "\nE-mail: " + email +
                "\nSenha: " + password.replace(password, "*".repeat(password.length())) +
                "\nData de Criação da Conta: " + dateTimeUtil.formatDateTime(accountDateTimeCreation);
    }

}
