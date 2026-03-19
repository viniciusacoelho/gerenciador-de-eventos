package repository;

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
        for (Participant participant : participants) {
            System.out.println(participant.toString());
            System.out.println("--------------------------------------------");
        }
    }

    // TODO: Find binary recursive
    public Participant findParticipantById(int participantId) {
        for (Participant participant : participants) {
            if (participant.getParticipantId() == participantId) {
                return participant;
            }
        }
        return null;
    }
}
