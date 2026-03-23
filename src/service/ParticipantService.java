package service;

public class ParticipantService {

    public boolean validateName(String name) {
        return name.length() > 3;
    }

    public boolean validateContact(int contact) {
        // TODO: Validate if contact have 10 digits
        return contact > 10;
    }

    // TODO: Contact formatter

}
