package repository;

import exceptions.EventNotFoundException;
import exceptions.EventNotRegisteredException;
import model.Event;

import java.time.LocalDateTime;
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

    public void readEvents() throws EventNotRegisteredException {
        if (events.isEmpty()) {
            throw new EventNotRegisteredException("Nenhum evento cadastrado anteriormente.");
        }

        for (Event event : events) {
            System.out.println(event);
            System.out.println("--------------------------------------------");
        }
    }

    public <T> void updateEvent(int eventId, T attribute, String attributeName) {
        Event event = findEventById(eventId);

        switch (attributeName) {
            case "Nome" -> event.setName((String) attribute);
            case "Horário" -> event.setDateTime((LocalDateTime) attribute);
            case "Local" -> event.setLocation((String) attribute);
            case "Capacidade" -> event.setCapacity((Integer) attribute);
            default -> System.out.println(attribute);
        }

        System.out.println(attributeName + " atualizado com sucesso!");
    }

    public void deleteEvent(Event event) {
        System.out.println("Participante '" + event.getName() + "' deletado com sucesso!");
        events.remove(event);
    }

    public Event findEventById(int eventId) throws EventNotRegisteredException, EventNotFoundException {
        // TODO: Test another way to use it, because this make a error, so I think I've no create a method to fix it
        if (events.isEmpty()) {
            throw new EventNotRegisteredException("Nenhum evento cadastrado anteriormente.");
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

        throw new EventNotFoundException("Evento não encontrado");
//        return null;
    }

    public Event findEventByName(String name) throws EventNotRegisteredException, EventNotFoundException {
        if (events.isEmpty()) {
            throw new IndexOutOfBoundsException("Nenhum evento cadastrado anteriormente.");
        }

        for (Event event : events) {
            if (event.getName().equalsIgnoreCase(name)) {
                return event;
            }
        }

        throw new EventNotFoundException("Evento não encontrado");
//        return null;
    }

}
