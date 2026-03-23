package repository;

import enums.Presence;
import model.Participant;

import java.util.ArrayList;
import java.util.List;

public class ParticipantRepository {

    private final List<Participant> participants;

    public ParticipantRepository() {
        this.participants = new ArrayList<>();
    }

    public void createParticipant(Participant participant) {
        this.participants.add(participant);
    }

    public void readParticipant() {
        if (this.participants.isEmpty()) {
            System.out.println("Nenhum participante cadastrado anteriormente.");
            return;
        }

        for (Participant participant : this.participants) {
            if (participant.getPresence() != Presence.CONFIRMED) {
                System.out.println("Nenhum participante confirmado anteriormente.");
                return;
            }
            System.out.println(participant);
            System.out.println("--------------------------------------------");
        }
    }

    // TODO: Find binary recursive
    public Participant findParticipantById(int participantId) {
        if (this.participants.isEmpty()) {
            System.out.println("Nenhum participante cadastrado anteriormente.");
            return null;
        }

        for (Participant participant : this.participants) {
            if (participant.getParticipantId() == participantId) {
                return participant;
            }
        }
        return null;
    }
}
