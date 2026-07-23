package service;

import enums.Attendance;
import exceptions.EventNotFoundException;
import exceptions.ParticipantEventNotFoundException;
import model.Event;
import model.Participant;

import static view.ParticipantView.participantRepository;

/**
 * Responsável por fornecer os serviços relacionados ao gerenciamento de
 * participantes, realizando operações de cadastro, consulta, validação,
 * atualização e remoção.
 */
public class ParticipantService {

    public static EventService eventService = new EventService();

    /**
     * Valida o nome de um participante de acordo com as regras definidas pelo sistema.
     *
     * @param name nome do participante.
     * @return {@code true} se o nome for válido; caso contrário, {@code false}.
     */
    public boolean validateName(String name) {
        return name.matches("^\\p{L}{2,}([\\s-]\\p{L}+)*$");
    }

    /**
     * Valida o contato de um participante de acordo com as regras definidas pelo sistema.
     *
     * @param contact contato do participante.
     * @return {@code true} se o contato for válido; caso contrário, {@code false}.
     */
    public boolean validateContact(String contact) {
        return contact.matches("^\\d{11}$");
    }

    /**
     * Formata o contato de um participante de acordo com o padrão adotado pelo sistema.
     *
     * @param contact contato do participante.
     * @return contato formatado.
     */
    public String formatContact(String contact) {
        return "(" +  contact.substring(0, 2) + ")" + " " + contact.substring(2, 7) + "-" + contact.substring(7, 11);
    }

    /**
     * Valida o endereço de e-mail de um participante de acordo com as regras definidas pelo sistema.
     *
     * @param email endereço de e-mail do participante.
     * @return {@code true} se o e-mail for válido; caso contrário, {@code false}.
     */
    public boolean validateEmail(String email) {
        return email.matches("^[^._+-][a-zA-Z._+-]+[^._+-]@[a-zA-Z0-9]([a-zA-Z0-9-])*[a-zA-Z0-9]+(\\.[a-zA-Z0-9]{2,})+$");
    }

    /**
     * Valida a senha de um participante de acordo com as regras definidas pelo sistema.
     *
     * @param password senha do participante.
     * @return {@code true} se a senha for válida; caso contrário, {@code false}.
     */
    public boolean validatePassword(String password) {
        return password.length() >= 8;
    }

    /**
     * Adiciona um evento à lista de eventos de um participante.
     *
     * @param event evento que será associado ao participante.
     * @param participant participante que será associado ao evento.
     */
    public void addEventParticipant(Event event, Participant participant) {
        participant.setEvents(event);
    }

    /**
     * Verifica se um participante já está inscrito em um determinado evento.
     *
     * @param participant participante que será verificado.
     * @param event evento cuja inscrição será verificada.
     * @return {@code true} se o participante já estiver inscrito no evento; caso contrário, {@code false}.
     */
    public boolean isEventRegistered(Participant participant, Event event) {
        return participant.getEvents().contains(event);
    }

    /**
     * Confirma a presença de um participante em um evento.
     *
     * @param participant participante que terá a presença confirmada.
     * @param event evento no qual a presença será confirmada.
     * @throws EventNotFoundException caso o evento não seja encontrado.
     */
    public void confirmAttendance(Participant participant, Event event) throws EventNotFoundException {
        if (!participant.getAttendanceEvents().containsKey(event)) {
            throw new EventNotFoundException("Evento inválido!");
        }

        participant.setAttendanceEvents(event, Attendance.CONFIRMED);
    }

    /**
     * Cancela a presença de um participante em um evento.
     *
     * @param participant participante que terá a presença cancelada.
     * @param event evento cuja presença será cancelada.
     * @throws EventNotFoundException caso o evento não seja encontrado.
     */
    public void cancelAttendance(Participant participant, Event event) throws EventNotFoundException {
        if (!participant.getAttendanceEvents().containsKey(event)) {
            throw new EventNotFoundException("Evento inválido!");
        }

        participant.setAttendanceEvents(event, Attendance.CANCELED);
        eventService.removeParticipant(participant, event);
    }

    /**
     * Verifica se uma presença foi confirmada.
     *
     * @param attendance presença que será verificada.
     * @return {@code true} se a presença estiver confirmada; caso contrário, {@code false}.
     */
    public boolean isAttendanceConfirmed(Attendance attendance) {
        return attendance == Attendance.CONFIRMED;
    }

    /**
     * Verifica se uma presença foi cancelada.
     *
     * @param attendance presença que será verificada.
     * @return {@code true} se a presença estiver cancelada; caso contrário, {@code false}.
     */
    public boolean isAttendanceCanceled(Attendance attendance) {
        return attendance == Attendance.CANCELED;
    }

    /**
     * Verifica se um participante não está inscrito em nenhum evento.
     *
     * @param participant participante que será verificado.
     * @return {@code true} se o participante não estiver inscrito em nenhum evento; caso contrário, {@code false}.
     */
    public boolean isEmpty(Participant participant) {
        return participant == null;
    }

    /**
     * Verifica se a senha informada atende aos critérios definidos pelo sistema.
     *
     * @param password senha que será verificada.
     * @return {@code true} se a senha estiver correta; caso contrário, {@code false}.
     */
    public boolean isPasswordCorrect(String password) {
        return participantRepository.findParticipantByPassword(password) != null;
    }

    /**
     * Verifica se um participante possui eventos cadastrados.
     *
     * @param participant participante que será verificado.
     * @throws ParticipantEventNotFoundException caso o participante não possua eventos cadastrados.
     */
    public void hasParticipantEventsRegistered(Participant participant) throws ParticipantEventNotFoundException {
        if (participant.getEvents().isEmpty()) {
            throw new EventNotFoundException("Nenhum evento inscrito anteriormente.");
        }
    }

    /**
     * Verifica se um determinado evento pertence à lista de eventos de um participante.
     *
     * @param participant participante que será verificado.
     * @param event evento que será verificado.
     * @return {@code true} se o evento estiver associado ao participante; caso contrário, {@code false}.
     */
    public boolean isParticipantEvent(Participant participant, Event event) {
        return event.getParticipants().contains(participant);
    }

}
