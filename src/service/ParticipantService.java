package service;

import enums.Attendance;
import model.Event;
import model.Participant;

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

    public void confirmPresence(Participant participant, int eventId) {
        participant.setAttendances(Attendance.CONFIRMED.getAttendance(), eventId);
        removePresence(participant, eventId);
    }

    public void cancelPresence(Participant participant, int eventId) {
        participant.setAttendances(Attendance.CANCELED.getAttendance(), eventId);
        removePresence(participant, eventId);
    }

    // TODO: Verify if I'll separate this in methods of each one or continue with this method doing everything
    public void removePresence(Participant participant, int eventId) {
        for (String attendance : participant.getAttendances().keySet()) {
            if (attendance.equals(Attendance.PENDING.getAttendance())) {
                participant.getAttendances().get(Attendance.PENDING.getAttendance()).remove(eventId);
            } else if (attendance.equals(Attendance.CONFIRMED.getAttendance())) {
                participant.getAttendances().get(Attendance.CONFIRMED.getAttendance()).remove(eventId);
            } else {
                participant.getAttendances().get(Attendance.CANCELED.getAttendance()).remove(eventId);
            }
        }
    }

}
