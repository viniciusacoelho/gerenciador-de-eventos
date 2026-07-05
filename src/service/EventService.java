package service;

import enums.Status;
import exceptions.EventFullException;
import exceptions.EventNotFoundException;
import model.Event;
import model.Participant;
import model.Ticket;

public class EventService {

    public boolean validateName(String name) {
        return name.matches("^\\p{L}{2,}([\\s-]\\p{L}+)*$");
    }

    public boolean validateLocation(String location) {
        return location.matches("^\\p{L}{2,}([\\s-]\\p{L}+)*$");
    }

    public boolean validateCapacity(int capacity) {
        return capacity > 0;
    }

    public void addParticipantEvent(Participant participant, Event event, Ticket ticket) throws EventFullException {
        if (!hasCapacity(event)) {
            // TODO: When I create the Ticket class, I'll put the SOLD_OUT enum here
            // TODO: I think I'll put it in other place
            ticket.setStatusEvents(Status.SOLD_OUT, event);
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

    public void removeParticipant(Participant participant, Event event) {
        event.getParticipants().remove(participant);
    }

    public void hasEvent(Event event) throws EventNotFoundException {
        if (isEmpty(event)) {
            throw new EventNotFoundException("Evento não encontrado. Tente novamente.");
        }
    }

}
