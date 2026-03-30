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
                throw new IndexOutOfBoundsException("Nenhum participante confirmado anteriormente.");
            }

            System.out.println(participant);
            System.out.println("--------------------------------------------");
        }
    }

    public Participant findParticipantById(int participantId) {
        if (this.participants.isEmpty()) {
            throw new IndexOutOfBoundsException("Nenhum participante cadastrado anteriormente.");
        }

        int start = 0;
        int end = participants.size() - 1;

        while (start <= end) {
            int middle = (end - start) / 2;
            if (participants.get(middle).getParticipantId() == participantId) {
                return participants.get(middle);
            } else if (participants.get(middle).getParticipantId() < participantId) {
                start = middle + 1;
            } else {
                end = middle - 1;
            }
        }

        return null;
    }
}
