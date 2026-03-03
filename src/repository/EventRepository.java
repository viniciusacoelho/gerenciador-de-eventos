package repository;

import model.Event;

import java.util.ArrayList;
import java.util.List;

public class EventRepository {

    private final List<Event> events;

    public EventRepository() {
        this.events = new ArrayList<>();
    }

    public void createEvent(Event event) {
        this.events.add(event);
    }

    public void readEvent() {
        // TODO: Validate if Event exists before
        for (Event event : events) {
            System.out.println(event.toString());
            System.out.println("--------------------------------------------");
        }
    }

}
