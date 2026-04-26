package service;

import enums.Attendance;
import model.Event;
import model.Participant;

import java.util.Objects;

import static view.EventView.eventRepository;
import static view.ParticipantView.participantRepository;

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

    public boolean isEventRegistered(Participant participant, Event event) {
        return participant.getEvents().contains(event);
    }

    public void confirmAttendance(Participant participant, Event event) {
        if (participant.getAttendances().containsKey(event)) {
            participant.getAttendances().put(event, Attendance.CONFIRMED);
            return;
        }

        System.out.println("Evento inválido!");
    }

    public void cancelAttendance(Participant participant, Event event) {
        if (participant.getAttendances().containsKey(event)) {
            participant.getAttendances().put(event, Attendance.CANCELED);
            return;
        }

        System.out.println("Evento inválido!");
    }

    public boolean isAttendanceConfirmed(Participant participant, Event event) {
        return Objects.equals(participant.getAttendances().get(event).getAttendance(), Attendance.CONFIRMED.getAttendance());
    }

    public boolean isEmpty(Participant participant) {
        return participant == null;
    }

    public boolean isPasswordCorrect(String password) {
        return participantRepository.findParticipantByPassword(password) != null;
    }

}
