package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Event {

    private static int totalRegisteredEvents = 1;

    private int eventId;

    private String name;

    private String date;

    private String location;

    private int capacity;

    private List<Participant> participants;

    public Event(String name, String date, String location, int capacity) {
        this.eventId = totalRegisteredEvents++;
        this.name = name;
        this.date = date;
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

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
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
        return "ID: " + this.getEventId() +
                "\nNome: " + this.getName() +
                "\nData: " + this.getDate() +
                "\nLocal: " + this.getLocation() +
                "\nCapacidade: " + this.getCapacity() +
                "\nNúmero de participantes confirmados: " + this.participants.toArray().length;
    }

}
