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
        // TODO: Create a method of this to reduce code lines (if this is possible)
        if (this.events.isEmpty()) {
            System.out.println("Nenhum evento cadastrado anteriormente.");
            return;
        }

        for (Event event : this.events) {
            System.out.println(event);
            System.out.println("--------------------------------------------");
        }
    }

    public Event findEventById(int eventId) {
//    TODO: Find binary normal
//        Test -> WORKED!
        if (this.events.isEmpty()) {
            throw new IndexOutOfBoundsException("Nenhum evento cadastrado anteriormente.");
        }

        int start = 0;
        int end = events.toArray().length - 1;

        while (start <= end) {
            int middle = (start + end) / 2;

            if (events.get(middle).getEventId() == eventId) {
                return events.get(middle);
            } else if (events.get(middle).getEventId() < eventId) {
                start = middle + 1;
            } else {
                end = middle - 1;
            }
        }

        return null;

//        if (this.events.isEmpty()) {
//            System.out.println("Nenhum evento cadastrado anteriormente.");
//            return null;
//        }
//
//        for (Event event : this.events) {
//            if (event.getEventId() == eventId) {
//                return event;
//            }
//        }
//
//        System.out.println("Evento não encontrado.");
//        return null;
    }

}
