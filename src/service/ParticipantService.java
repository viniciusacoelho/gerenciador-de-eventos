package service;

import enums.Attendance;
import model.Event;
import model.Participant;

import static view.EventView.eventRepository;

public class ParticipantService {

    public boolean validateName(String name) {
        return name.length() > 3;
    }

    public boolean validateContact(int contact) {
        // TODO: Validate if contact have 10 digits
        return contact > 10;
    }

    // TODO: Contact formatter
    public String contactFormatter(int contact) {
        return "Em breve";
    }

    // TODO: Regex validation
    public boolean validateEmail(String email) {
        return email.length() > 3;
    }

    // TODO: Regex validation
    public boolean validatePassword(String password) {
        return password.length() > 3;
    }

    public void addEventParticipant(Event event, Participant participant) {
//        participant.setPresences(Presence.PENDING); // TODO: Verify if need this
        participant.setEvents(event);
    }

    public boolean verifyRegisteredEvent(Participant participant, int eventId) {
        Event event = eventRepository.findEventById(eventId);
        return participant.getEvents().contains(event);
    }

    public void confirmPresence(Participant participant, Event event) {
        if (participant.getAttendances().containsKey(event)) {
            participant.getAttendances().put(event, Attendance.CONFIRMED);
            return;
        }

        System.out.println("Evento inválido!");
    }

    public void cancelPresence(Participant participant, Event event) {
        if (participant.getAttendances().containsKey(event)) {
            participant.getAttendances().put(event, Attendance.CANCELED);
            return;
        }

        System.out.println("Evento inválido!");
    }

}
