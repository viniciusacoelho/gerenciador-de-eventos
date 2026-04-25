package service;

import exceptions.EventCapacityException;
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

    public void addParticipantEvent(Participant participant, Event event) throws EventCapacityException {
        if (!haveCapacity(event)) {
            throw new EventCapacityException("Evento lotado! Não foi possível inscrever o participante '" + participant.getName() + "'.");
            // TODO: When I create the class Ticket, I'll put the enum SOLD_OUT here
        }

        event.setParticipants(participant);
        System.out.println("Participante '" + participant.getName() + "' inscrito no evento '" + event.getName() + "' com sucesso!");
    }

    public boolean haveCapacity(Event event) {
        return event.getCapacity() > event.getParticipants().size();
    }

    public boolean isEmpty(Event event) {
        return event == null;
    }
}
