package service;

import model.Event;
import model.Participant;

public class EventService {

    public boolean validateName(String name) {
        return name.length() > 3;
    }

    public boolean validateDate(String date) {
        return date.length() == 10;
    }

    public boolean validateLocation(String location) {
        return location.length() > 3;
    }

    public boolean validateCapacity(int capacity) {
        return capacity > 0;
    }

    // TODO: Date formatter and use LocalDateTime

    public void addParticipantEvent(Participant participant, Event event) {
        if (event.getParticipants().toArray().length < event.getCapacity()) {
            event.setParticipants(participant);
            System.out.println("Participante '" + participant.getName() + "' inscrito no evento '" + event.getName() + "' com sucesso!");
            return;
        }
        System.out.println("Evento lotado! Não foi possível inscrever o participante.");
    }

}
