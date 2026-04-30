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
        return name.length() > 3;
    }

    // TODO: Validate if contact have 10 digits
    public boolean validateContact(int contact) {
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

    public boolean isEventRegistered(Participant participant, Event event) {
        return participant.getEvents().contains(event);
    }

    public void confirmAttendance(Participant participant, Event event) throws EventNotFoundException {
        if (!participant.getAttendances().containsKey(event)) {
            throw new EventNotFoundException("Evento inválido!");
        }

        participant.getAttendances().put(event, Attendance.CONFIRMED);
    }

    public void cancelAttendance(Participant participant, Event event) throws EventNotFoundException {
        if (!participant.getAttendances().containsKey(event)) {
            throw new EventNotFoundException("Evento inválido!");
        }

        participant.getAttendances().put(event, Attendance.CANCELED);
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
