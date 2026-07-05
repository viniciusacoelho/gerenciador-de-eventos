package model;

import util.DateTimeUtil;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    public Event(String name, LocalDateTime dateTime, String location, int capacity) {
        this.eventId = totalRegisteredEvents++;
        this.name = name;
        this.dateTime = dateTime;
        this.location = location;
        this.capacity = capacity;
        this.participants = new ArrayList<>();
        this.tickets = new ArrayList<>();
    }

    public Event() {
    }

    public int getTotalRegisteredEvents() {
        return totalRegisteredEvents;
    }

    public int getEventId() {
        return eventId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getDateTime() {
        return this.dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getCapacity() {
        return this.capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public List<Participant> getParticipants() {
        return participants;
    }

    public void setParticipants(Participant participant) {
        this.participants.add(participant);
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(Ticket ticket) {
        this.tickets.add(ticket);
    }

    @Override
    public String toString() {
        return "ID: " + eventId +
                "\nNome: " + name +
                "\nHorário: " + dateTimeUtil.formatDateTime(dateTime) +
                "\nLocal: " + location +
                "\nCapacidade: " + capacity +
                "\nNúmero de participantes confirmados: " + confirmedParticipants();
    }

    public int confirmedParticipants() {
        return participants.toArray().length;
    }

}
