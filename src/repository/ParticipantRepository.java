package repository;

import exceptions.ParticipantEventNotFoundException;
import exceptions.ParticipantNotFoundException;
import model.Event;
import model.Participant;
import service.ParticipantService;

import java.util.ArrayList;
import java.util.List;

public class ParticipantRepository {

    private final List<Participant> participants;

    private final ParticipantService participantService = new ParticipantService();

    public ParticipantRepository() {
        participants = new ArrayList<>();
    }

    public void createParticipant(Participant participant) {
        this.participants.add(participant);
    }

    public void readParticipants() throws ParticipantNotFoundException {
        if (participants.isEmpty()) {
            throw new ParticipantNotFoundException("Nenhum participante cadastrado anteriormente.");
        }

        for (Participant participant : participants) {
            System.out.println(participant);
            System.out.println("--------------------------------------------");
        }
    }

    public void readParticipantEvents(Participant participant) throws ParticipantEventNotFoundException {
        if (participant.getEvents().isEmpty()) {
            throw new ParticipantEventNotFoundException("Participante inscrito em nenhum evento anteriormente.");
        }

        for (Event event : participant.getEvents()) {
            if (!participantService.isAttendanceConfirmed(participant.getAttendances().get(event))) {
                System.out.println(event);
                System.out.println("Presença: " + participant.getAttendances().get(event).getAttendance());
                System.out.println("--------------------------------------------");
            }
        }
    }

    public <T> void updateParticipant(Participant participant, T attribute, String attributeName) {
        switch (attributeName) {
            case "Nome" -> participant.setName((String) attribute);
            case "Contato" -> participant.setContact((Integer) attribute);
            case "E-mail" -> participant.setEmail((String) attribute);
            case "Senha" -> participant.setPassword((String) attribute);
            default -> System.out.println("[ERRO]: Atributo inválido!");
        }

        System.out.println(attributeName + " atualizado com sucesso!");
    }

    public void deleteParticipant(Participant participant) {
        System.out.println("Participante '" + participant.getName() + "' deletado com sucesso!");
        participants.remove(participant);
    }

    public Participant findParticipantById(int participantId) throws ParticipantNotFoundException {
        if (participants.isEmpty()) {
            throw new ParticipantNotFoundException("Nenhum participante cadastrado anteriormente.");
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

    public Participant findParticipantByEmail(String email) throws ParticipantNotFoundException {
        if (participants.isEmpty()) {
            throw new ParticipantNotFoundException("Nenhum participante cadastrado anteriormente.");
        }

        for (Participant participant : participants) {
            if (email.equals(participant.getEmail())) {
                return participant;
            }
        }

        return null;
    }

    public Participant findParticipantByPassword(String password) throws ParticipantNotFoundException {
        if (participants.isEmpty()) {
            throw new ParticipantNotFoundException("Nenhum participante cadastrado anteriormente.");
        }

        for (Participant participant : participants) {
            if (password.equals(participant.getPassword())) {
                return participant;
            }
        }

        return null;
    }

}
