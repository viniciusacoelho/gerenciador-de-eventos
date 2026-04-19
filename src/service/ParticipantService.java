package service;

import enums.Presence;
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

}
