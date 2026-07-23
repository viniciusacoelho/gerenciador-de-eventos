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

/**
 * Responsável por armazenar e gerenciar os participantes cadastrados no sistema.
 */
public class ParticipantRepository {

    private final List<Participant> participants;

    private int counterConfirmedEvents;

    private int counterCanceledEvents;

    private static final ParticipantService participantService = new ParticipantService();

    /**
     * Cria um repositório de participantes.
     */
    public ParticipantRepository() {
        participants = new ArrayList<>();
    }

    /**
     * Retorna a lista de participantes cadastrados no repositório.
     *
     * @return lista de participantes cadastrados.
     */
    public List<Participant> getParticipants() {
        return participants;
    }

    /**
     * Adiciona um novo participante ao repositório.
     *
     * @param participant participante que será cadastrado.
     */
    public void createParticipant(Participant participant) {
        this.participants.add(participant);
    }

    /**
     * Exibe todos os participantes cadastrados no sistema.
     *
     * @throws ParticipantNotFoundException caso não exista nenhum participante cadastrado.
     */
    public void readParticipants() throws ParticipantNotFoundException {
        if (participants.isEmpty()) {
            throw new ParticipantNotFoundException("Nenhum participante cadastrado anteriormente.");
        }

        for (Participant participant : participants) {
            System.out.println(participant);
            System.out.println("--------------------------------------------");
        }
    }

    /**
     * Exibe os eventos nos quais um participante está inscrito.
     *
     * @param participant participante cujos eventos serão exibidos.
     */
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

    /**
     * Exibe os eventos do participante que ainda não possuem presença confirmada.
     *
     * @param participant participante cujos eventos não confirmados serão exibidos.
     */
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

    /**
     * Exibe os eventos do participante que não foram cancelados.
     *
     * @param participant participante cujos eventos ativos serão exibidos.
     */
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

    /**
     * Exibe os eventos nos quais o participante ainda não está inscrito.
     *
     * @param participant participante cujos eventos disponíveis serão exibidos.
     */
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

    /**
     * Atualiza um atributo específico de um participante.
     *
     * @param participant participante que será atualizado.
     * @param attribute novo valor do atributo.
     * @param attributeName nome do atributo que será atualizado.
     * @param <T> tipo do atributo informado.
     */
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

    /**
     * Remove um participante do repositório.
     *
     * @param participant participante que será removido.
     */
    public void deleteParticipant(Participant participant) {
        System.out.println("Participante '" + participant.getName() + "' deletado com sucesso!");
        participants.remove(participant);
    }

    /**
     * Busca um participante pelo seu identificador.
     *
     * @param participantId identificador do participante.
     * @return participante correspondente ao identificador informado.
     * @throws ParticipantNotFoundException caso o participante não seja encontrado.
     */
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

    /**
     * Busca um participante pelo endereço de e-mail.
     *
     * @param email endereço de e-mail do participante.
     * @return participante correspondente ao e-mail informado.
     * @throws ParticipantNotFoundException caso o participante não seja encontrado.
     */
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

    /**
     * Busca um participante pela senha cadastrada.
     *
     * @param password senha do participante.
     * @return participante correspondente à senha informada.
     * @throws ParticipantNotFoundException caso o participante não seja encontrado.
     */
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

    /**
     * Retorna a quantidade de eventos com presença confirmada.
     *
     * @return quantidade de eventos confirmados.
     */
    public int getCounterConfirmedEvents() {
        return counterConfirmedEvents;
    }

    /**
     * Define a quantidade de eventos com presença confirmada.
     *
     * @param counterConfirmedEvents nova quantidade de eventos confirmados.
     */
    public void setCounterConfirmedEvents(int counterConfirmedEvents) {
        this.counterConfirmedEvents = counterConfirmedEvents;
    }

    /**
     * Retorna a quantidade de eventos cancelados.
     *
     * @return quantidade de eventos cancelados.
     */
    public int getCounterCanceledEvents() {
        return counterCanceledEvents;
    }

    /**
     * Define a quantidade de eventos cancelados.
     *
     * @param counterCanceledEvents nova quantidade de eventos cancelados.
     */
    public void setCounterCanceledEvents(int counterCanceledEvents) {
        this.counterCanceledEvents = counterCanceledEvents;
    }
}
