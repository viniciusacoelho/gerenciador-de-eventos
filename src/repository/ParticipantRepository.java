package repository;

import model.Event;
import model.Participant;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ParticipantRepository {

    private final List<Participant> participants;

    public ParticipantRepository() {
        participants = new ArrayList<>();
    }

    public void createParticipant(Participant participant) {
        this.participants.add(participant);
    }

    public void readParticipants() {
        if (participants.isEmpty()) {
            System.out.println("Nenhum participante cadastrado anteriormente.");
            return;
        }

        for (Participant participant : this.participants) {
            // TODO: Fix it
//            if (participant.getPresence() != Presence.CONFIRMED) {
//                throw new IndexOutOfBoundsException("Nenhum participante confirmado anteriormente.");
//            }

            System.out.println(participant);
            System.out.println("--------------------------------------------");
        }
    }

    public void readParticipantEvents(Participant participant) {
        if (participant.getEvents().isEmpty()) {
            System.out.println("Participante inscrito em nenhum evento anteriormente.");
            return;
        }

        for (Event event : participant.getEvents()) {
            System.out.println(event);
            System.out.println("--------------------------------------------");
        }

        // This work too, but I need to import from EventRepository
//        for (Event event : eventRepository.getEvents()) {
//            if (event.getParticipants().contains(participant)) {
//                System.out.println(event);
//                System.out.println("--------------------------------------------");
//            }
//        }

    }

    public <T> void updateParticipant(int participantId, T attribute, String attributeName) {
        Participant participant = findParticipantById(participantId);

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

    public Participant findParticipantByEmail(String email) {
        if (participants.isEmpty()) {
            return null;
        }

        for (Participant participant : participants) {
            if (Objects.equals(email, participant.getEmail())) {
                return participant;
            }
        }

        return null;
    }

    public Participant findParticipantByPassword(String password) {
        if (participants.isEmpty()) {
            return null;
        }

        for (Participant participant : participants) {
            if (Objects.equals(password, participant.getPassword())) {
                return participant;
            }
        }

        return null;
    }

}
