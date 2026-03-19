package model;

import service.EventService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Event {

    private static int totalRegisteredEvents = 1;

    private int eventId;

    private String name;

    private LocalDateTime dateTime;

    private String location;

    private int capacity;

    private List<Participant> participants;

    public Event(String name, LocalDateTime dateTime, String location, int capacity) {
        this.eventId = totalRegisteredEvents++;
        this.name = name;
        this.dateTime = dateTime;
        this.location = location;
        this.capacity = capacity;
        this.participants = new ArrayList<>();
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

    public void setParticipants(Participant participants) {
        this.participants = Collections.singletonList(participants);
    }

    @Override
    public String toString() {
        EventService eventService = new EventService();
        return "ID: " + this.eventId +
                "\nNome: " + this.name +
                "\nHorário: " + eventService.formatDateTime(this.dateTime) +
                "\nLocal: " + this.location +
                "\nCapacidade: " + this.capacity +
                "\nNúmero de participantes confirmados: " + this.participants.toArray().length;
    }

}
