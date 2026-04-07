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
            throw new IndexOutOfBoundsException("Nenhum evento cadastrado anteriormente.");
        }

        for (Event event : this.events) {
            System.out.println(event);
            System.out.println("--------------------------------------------");
        }
    }

    public void updateEvent(int eventId, String attribute, String attributeName) {
//        TODO: Fix, because the user will choose a number or LocalDateTime to update de attribute
        Event event = findEventById(eventId);
        switch (attributeName) {
            case "Nome" -> event.setName(attribute);
//            case "dateTime" -> event.setDateTime();
            case "dateTime" -> event.setLocation(attribute);
//            case "capacity" -> event.setCapacity(attribute);
            default -> System.out.println(attribute);
        }
        System.out.println(attributeName + " atualizado com sucesso!");
    }

    public void deleteEvent(Event event) {
        System.out.println("Participante '" + event.getName() + "' deletado com sucesso!");
        events.remove(event);
    }

    public Event findEventById(int eventId) {
        // TODO: Test another way to use it, because this make a error, so I think I've no create a method to fix it
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
    }

    public Event findEventByName(String name) {
        if (events.isEmpty()) {
            throw new IndexOutOfBoundsException("Nenhum evento cadastrado anteriormente.");
        }

        for (Event event : events) {
            if (event.getName().equalsIgnoreCase(name)) {
                return event;
            }
        }
        return null;
    }

}
