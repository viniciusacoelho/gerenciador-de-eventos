package model;

import enums.Presence;
import util.DateTimeUtil;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Participant {

    public static int totalRegisteredParticipants = 1;

    private int participantId;

    private String name;

    private int contact;

    private String email;

    private String password;

    private LocalDateTime accountDateTimeCreation;

    private List<Event> events;

    private List<Presence> presences;

    private final static DateTimeUtil dateTimeUtil = new DateTimeUtil();

    public Participant(String name, int contact, String email, String password) {
        this.participantId = totalRegisteredParticipants++;
        this.name = name;
        this.contact = contact;
        this.email = email;
        this.password = password;
        this.accountDateTimeCreation = LocalDateTime.now();
        this.events = new ArrayList<>();
        this.presences = new ArrayList<>();
    }

    public Participant() {
    }

    public int getTotalRegisteredParticipants() {
        return totalRegisteredParticipants;
    }

    public int getParticipantId() {
        return participantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getContact() {
        return contact;
    }

    public void setContact(int contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(Event event) {
        this.events.add(event);
        setPresences(Presence.PENDING);
    }

    public List<Presence> getPresences() {
        return presences;
    }

    public void setPresences(Presence presence) {
        this.presences.add(presence);
    }

    public LocalDateTime getAccountDateTimeCreation() { // TODO: Verify if it's necessary, if the user will use it in the future
        return accountDateTimeCreation;
    }

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
