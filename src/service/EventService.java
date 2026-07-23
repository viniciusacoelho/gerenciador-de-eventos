package service;

import enums.Status;
import exceptions.EventFullException;
import exceptions.EventNotFoundException;
import model.Event;
import model.Participant;
import model.Ticket;

/**
 * Responsável por fornecer os serviços relacionados ao gerenciamento de eventos,
 * realizando operações de cadastro, consulta, atualização e remoção.
 */
public class EventService {

    /**
     * Valida o nome de um evento de acordo com as regras definidas pelo sistema.
     *
     * @param name nome do evento.
     * @return {@code true} se o nome for válido; caso contrário, {@code false}.
     */
    public boolean validateName(String name) {
        return name.matches("^\\p{L}{2,}([\\s-]\\p{L}+)*$");
    }

    /**
     * Valida a localização de um evento de acordo com as regras definidas pelo sistema.
     *
     * @param location localização do evento.
     * @return {@code true} se a localização for válida; caso contrário, {@code false}.
     */
    public boolean validateLocation(String location) {
        return location.matches("^\\p{L}{2,}([\\s-]\\p{L}+)*$");
    }

    /**
     * Valida a capacidade de um evento de acordo com as regras definidas pelo sistema.
     *
     * @param capacity capacidade máxima de participantes do evento.
     * @return {@code true} se a capacidade for válida; caso contrário, {@code false}.
     */
    public boolean validateCapacity(int capacity) {
        return capacity > 0;
    }

    /**
     * Inscreve um participante em um evento utilizando um ingresso.
     *
     * @param participant participant participante que será inscrito.
     * @param event evento no qual o participante será inscrito.
     * @param ticket ingresso utilizado na inscrição.
     * @throws EventFullException caso o evento tenha atingido sua capacidade máxima.
     */
    public void addParticipantEvent(Participant participant, Event event, Ticket ticket) throws EventFullException {
        if (!hasCapacity(event)) {
            ticket.setStatusEvents(Status.SOLD_OUT, event);
            throw new EventFullException("Evento lotado! Não foi possível inscrever o participante '" + participant.getName() + "'.");
        }

        event.setParticipants(participant);
        System.out.println("Participante '" + participant.getName() + "' inscrito no evento '" + event.getName() + "' com sucesso!");
    }

    /**
     * Verifica se o evento ainda possui vagas disponíveis.
     *
     * @param event evento que será verificado.
     * @return {@code true} se o evento possuir vagas disponíveis; caso contrário, {@code false}.
     */
    private boolean hasCapacity(Event event) {
        return event.getCapacity() > event.getParticipants().size();
    }

    /**
     * Verifica se um evento não possui participantes inscritos.
     *
     * @param event evento que será verificado.
     * @return {@code true} se o evento não possuir participantes; caso contrário, {@code false}.
     */
    public boolean isEmpty(Event event) {
        return event == null;
    }

    /**
     * Verifica se o evento possui participantes cadastrados.
     *
     * @param event evento que será verificado.
     * @return {@code true} se o evento possuir participantes cadastrados; caso contrário, {@code false}.
     */
    public boolean hasEventsRegistered(Event event) {
        return event.getTotalRegisteredEvents() == 0;
    }

    /**
     * Remove um participante de um evento.
     *
     * @param participant participante que será removido do evento.
     * @param event evento do qual o participante será removido.
     */
    public void removeParticipant(Participant participant, Event event) {
        event.getParticipants().remove(participant);
    }

    /**
     * Verifica se um evento existe no sistema.
     *
     * @param event evento que será verificado.
     * @throws EventNotFoundException caso o evento não exista.
     */
    public void hasEvent(Event event) throws EventNotFoundException {
        if (isEmpty(event)) {
            throw new EventNotFoundException("Evento não encontrado. Tente novamente.");
        }
    }

}
