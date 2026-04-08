package model;

import enums.Presence;

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

    public Participant(String name, int contact, String email, String password) {
        this.participantId = totalRegisteredParticipants++;
        this.name = name;
        this.contact = contact;
        this.email = email;
        this.password = password;
        this.presence = Presence.PENDING;
        this.events = new ArrayList<>();
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
        this.events.add(event);
    }

    @Override
    public String toString() {
        return "ID: " + this.getParticipantId() +
                "\nNome: " + this.getName() +
                "\nContato:" + this.getContact() +
                "\nPresença: " + this.getPresence().getPresence();
    }

}
