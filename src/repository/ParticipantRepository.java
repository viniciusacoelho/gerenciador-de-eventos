package repository;

import enums.Status;
import exceptions.EventNotFoundException;
import exceptions.ParticipantEventNotFoundException;
import exceptions.ParticipantNotFoundException;
import model.Event;
import model.Participant;
import model.Ticket;
import service.ParticipantService;

import java.util.ArrayList;
import java.util.List;

import static view.EventView.*;

public class ParticipantRepository {

    private final List<Participant> participants;

    private int counterConfirmedEvents;

    private int counterCanceledEvents;

    private static final ParticipantService participantService = new ParticipantService();

    public ParticipantRepository() {
        participants = new ArrayList<>();
    }

    public List<Participant> getParticipants() {
        return participants;
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

    public void readParticipantEvents(Participant participant) {
        try {
            participantService.hasParticipantEventsRegistered(participant);
        } catch (ParticipantEventNotFoundException e) {
            System.out.println(e.getMessage());
        }

        for (Event event : participant.getEvents().reversed()) {
            System.out.println(event);
            System.out.println("Presença: " + participant.getAttendanceEvents().get(event).getAttendance());
            System.out.println("Ingresso: " + participant.getEventTickets().get(event).getName());
            System.out.println("--------------------------------------------");
        }
    }

    public void readParticipantEventsNotConfirmed(Participant participant) {
        try {
            participantService.hasParticipantEventsRegistered(participant);
        } catch (ParticipantEventNotFoundException e) {
            System.out.println(e.getMessage());
        }

        for (Event event : participant.getEvents()) {
            if (!participantService.isAttendanceConfirmed(participant.getAttendanceEvents().get(event))) {
                System.out.println(event);
                System.out.println("Presença: " + participant.getAttendanceEvents().get(event).getAttendance());
                System.out.println("--------------------------------------------");
                this.counterConfirmedEvents++;
            }
        }
    }

    public void readParticipantEventsNotCanceled(Participant participant) {
        try {
            participantService.hasParticipantEventsRegistered(participant);
        } catch (ParticipantEventNotFoundException e) {
            System.out.println(e.getMessage());
        }

        for (Event event : participant.getEvents()) {
            if (!participantService.isAttendanceCanceled(participant.getAttendanceEvents().get(event))) {
                System.out.println(event);
                System.out.println("Presença: " + participant.getAttendanceEvents().get(event).getAttendance());
                System.out.println("--------------------------------------------");
                this.counterCanceledEvents++;
            }
        }
    }

    public void readParticipantEventsNotRegistered(Participant participant) {
        if (eventRepository.getEvents().isEmpty()) {
            throw new EventNotFoundException("Nenhum evento cadastrado anteriormente.");
        }

        for (Event event : eventRepository.getEvents()) {
            if (!participantService.isParticipantEvent(participant, event) && !event.getTickets().stream().map(Ticket::getStatus).toList().contains(Status.SOLD_OUT)) {
                System.out.println(event);
                event.getTickets().stream()
                        .map(Ticket::getStatus)
                        .forEach(System.out::println);
                System.out.println("--------------------------------------------");
            }
        }
    }

    public <T> void updateParticipant(Participant participant, T attribute, String attributeName) {
        switch (attributeName) {
            case "Nome" -> participant.setName((String) attribute);
            case "Contato" -> participant.setContact((String) attribute);
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

    public int getCounterConfirmedEvents() {
        return counterConfirmedEvents;
    }

    public void setCounterConfirmedEvents(int counterConfirmedEvents) {
        this.counterConfirmedEvents = counterConfirmedEvents;
    }

    public int getCounterCanceledEvents() {
        return counterCanceledEvents;
    }

    public void setCounterCanceledEvents(int counterCanceledEvents) {
        this.counterCanceledEvents = counterCanceledEvents;
    }
}
