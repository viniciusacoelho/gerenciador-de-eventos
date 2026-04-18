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

    private Presence presence;

    private List<Event> events;

    private LocalDateTime accountDateTimeCreation;

    private final static DateTimeUtil dateTimeUtil = new DateTimeUtil();

    public Participant(String name, int contact, String email, String password) {
        this.participantId = totalRegisteredParticipants++;
        this.name = name;
        this.contact = contact;
        this.email = email;
        this.password = password;
        this.presence = Presence.PENDING;
        this.events = new ArrayList<>();
        this.accountDateTimeCreation = LocalDateTime.now();
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

    public Presence getPresence() {
        return presence;
    }

    public void setPresence(Presence presence) {
        this.presence = presence;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(Event event) {
        // TODO: Find a way to participant have one presence in each event
        this.presence = Presence.PENDING;

        this.events.add(event);
    }

    public LocalDateTime getAccountDateTimeCreation() { // TODO: Verify if it's necessary, if the user will use it in the future
        return accountDateTimeCreation;
    }

    @Override
    public String toString() {
        return "ID: " + participantId +
                "\nNome: " + name +
                "\nContato:" + contact +
                "\nPresença: " + presence + // TODO: Maybe remove it, because I think is better to see it in the Events part
                "\nData de Criação da Conta: " + dateTimeUtil.formatDateTime(accountDateTimeCreation);
    }

}
