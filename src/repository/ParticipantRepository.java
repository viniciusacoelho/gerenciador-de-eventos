package repository;

import model.Participant;
import service.ParticipantService;

import java.util.ArrayList;
import java.util.List;

public class ParticipantRepository {

    private final List<Participant> participants;

    ParticipantService participantService = new ParticipantService();

    public ParticipantRepository() {
        this.participants = new ArrayList<>();
    }

    public void createParticipant(Participant participant) {
        this.participants.add(participant);
    }

    public void readParticipant() {
        if (participantService.participantIsEmpty(participants)) {
            System.out.println("Nenhum participante cadastrado anteriormente.");
            return;
        }
        for (Participant participant : participants) {
            System.out.println(participant);
            System.out.println("--------------------------------------------");
        }
    }

    // TODO: Find binary recursive
    public Participant findParticipantById(int participantId) {
        if (participantService.participantIsEmpty(participants)) {
            System.out.println("Nenhum participante cadastrado anteriormente.");
            return null;
        }

        for (Participant participant : participants) {
            if (participant.getParticipantId() == participantId) {
                return participant;
            }
        }
        return null;
    }
}
