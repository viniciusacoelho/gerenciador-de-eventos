package service;

import exceptions.EventFullException;
import exceptions.EventNotFoundException;
import exceptions.ParticipantEventNotFoundException;
import model.Event;
import model.Participant;

public class EventService {

    public boolean validateName(String name) {
        return name.length() > 3;
    }

    public boolean validateLocation(String location) {
        return location.length() > 3;
    }

    public boolean validateCapacity(int capacity) {
        return capacity > 0;
    }

    public void addParticipantEvent(Participant participant, Event event) throws EventFullException {
        if (!hasCapacity(event)) {
            // TODO: When I create the Ticket class, I'll put the SOLD_OUT enum here
            throw new EventFullException("Evento lotado! Não foi possível inscrever o participante '" + participant.getName() + "'.");
        }

        event.setParticipants(participant);
        System.out.println("Participante '" + participant.getName() + "' inscrito no evento '" + event.getName() + "' com sucesso!");
    }

    private boolean hasCapacity(Event event) {
        return event.getCapacity() > event.getParticipants().size();
    }

    public boolean isEmpty(Event event) {
        return event == null;
    }

    public boolean hasEventsRegistered(Event event) {
        return event.getTotalRegisteredEvents() == 0;
    }

    public void hasParticipantEventsRegistered(Participant participant) throws ParticipantEventNotFoundException {
        if (participant.getEvents().isEmpty()) {
            throw new EventNotFoundException("Nenhum evento inscrito anteriormente.");
        }
    }

}
