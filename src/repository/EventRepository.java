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
//        Test ->
//        if (this.events.isEmpty()) {
//            int start = 0;
//            int end = events.toArray().length - 1;
//            int middle = (end + start) / 2;
//            while (end > start) {
//                if (events[middle] == eventId) {
//                    return event;
//                }
//            }
//        }

        if (this.events.isEmpty()) {
            System.out.println("Nenhum evento cadastrado anteriormente.");
            return null;
        }

        for (Event event : this.events) {
            if (event.getEventId() == eventId) {
                return event;
            }
        }

        System.out.println("Evento não encontrado.");
        return null;
    }

}
