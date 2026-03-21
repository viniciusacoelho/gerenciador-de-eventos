package service;

import model.Event;
import model.Participant;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

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

    public boolean EventIsEmpty(List<Event> events) {
        return events == null;
    }

    public LocalDateTime convertDateTime(String dateTime) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return LocalDateTime.parse(dateTime, dateTimeFormatter);
    }

    public String formatDateTime(LocalDateTime dateTime) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return dateTime.format(dateTimeFormatter);
    }

    public void addParticipantEvent(Participant participant, Event event) {
        if (event.getParticipants().toArray().length < event.getCapacity()) {
            event.setParticipants(participant);
            System.out.println("Participante '" + participant.getName() + "' inscrito no evento '" + event.getName() + "' com sucesso!");
            return;
        }
        System.out.println("Evento lotado! Não foi possível inscrever o participante.");
    }

}
