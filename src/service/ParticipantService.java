package service;

import enums.Attendance;
import exceptions.EventNotFoundException;
import exceptions.ParticipantEventNotFoundException;
import model.Event;
import model.Participant;

import static view.ParticipantView.participantRepository;

public class ParticipantService {

    public static EventService eventService = new EventService();

    public boolean validateName(String name) {
        return name.matches("^\\p{L}{2,}([\\s-]\\p{L}+)*$");
    }

    public boolean validateContact(String contact) {
        return contact.matches("^\\d{11}$");
    }

    public String formatContact(String contact) {
        return "(" +  contact.substring(0, 2) + ")" + " " + contact.substring(2, 7) + "-" + contact.substring(7, 11);
    }

    public boolean validateEmail(String email) {
        return email.matches("^[^._+-][a-zA-Z._+-]+[^._+-]@[a-zA-Z0-9]([a-zA-Z0-9-])*[a-zA-Z0-9]+(\\.[a-zA-Z0-9]{2,})+$");
    }

    public boolean validatePassword(String password) {
        return password.length() >= 8;
    }

    public void addEventParticipant(Event event, Participant participant) {
//        participant.setPresences(Presence.PENDING); // TODO: Verify if need this
        participant.setEvents(event);
    }

    public boolean isEventRegistered(Participant participant, Event event) {
        return participant.getEvents().contains(event);
    }

    public void confirmAttendance(Participant participant, Event event) throws EventNotFoundException {
        if (!participant.getAttendanceEvents().containsKey(event)) {
            throw new EventNotFoundException("Evento inválido!");
        }

        participant.setAttendanceEvents(event, Attendance.CONFIRMED);
    }

    public void cancelAttendance(Participant participant, Event event) throws EventNotFoundException {
        if (!participant.getAttendanceEvents().containsKey(event)) {
            throw new EventNotFoundException("Evento inválido!");
        }

        participant.setAttendanceEvents(event, Attendance.CANCELED);
        eventService.removeParticipant(participant, event);
    }

    public boolean isAttendanceConfirmed(Attendance attendance) {
        return attendance == Attendance.CONFIRMED;
    }

    public boolean isAttendanceCanceled(Attendance attendance) {
        return attendance == Attendance.CANCELED;
    }

    public boolean isEmpty(Participant participant) {
        return participant == null;
    }
//    public void hasParticipant(Participant participant) throws ParticipantNotFoundException {
//        if (participant == null) {
//            throw new ParticipantNotFoundException("");
//        }
//    }

    public boolean isPasswordCorrect(String password) {
        return participantRepository.findParticipantByPassword(password) != null;
    }

    public void hasParticipantEventsRegistered(Participant participant) throws ParticipantEventNotFoundException {
        if (participant.getEvents().isEmpty()) {
            throw new EventNotFoundException("Nenhum evento inscrito anteriormente.");
        }
    }

}
