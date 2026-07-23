package repository;

import exceptions.EventNotFoundException;
import model.Event;
import model.Ticket;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Responsável por armazenar e gerenciar os eventos cadastrados no sistema.
 */
public class EventRepository {

    private final List<Event> events;

    /**
     * Cria um repositório de eventos.
     */
    public EventRepository() {
        this.events = new ArrayList<>();
    }

    /**
     * Retorna a lista de eventos cadastrados no repositório.
     *
     * @return lista de eventos cadastrados.
     */
    public List<Event> getEvents() {
        return events;
    }

    /**
     * Adiciona um novo evento ao repositório.
     *
     * @param event evento que será cadastrado.
     */
    public void createEvent(Event event) {
        this.events.add(event);
    }

    /**
     * Exibe todos os eventos cadastrados no sistema.
     *
     * @throws EventNotFoundException caso não exista nenhum evento cadastrado.
     */
    public void readEvents() throws EventNotFoundException {
        if (events.isEmpty()) {
            throw new EventNotFoundException("Nenhum evento cadastrado anteriormente.");
        }

        for (Event event : events) {
            System.out.println(event);
            event.getTickets().stream()
                    .map(Ticket::getStatus)
                    .forEach(System.out::println);
            System.out.println("--------------------------------------------");
        }
    }

    /**
     * Atualiza um atributo específico de um evento.
     *
     * @param eventId identificador do evento que será atualizado.
     * @param attribute novo valor do atributo.
     * @param attributeName nome do atributo que será atualizado.
     * @param <T> tipo do atributo informado
     */
    public <T> void updateEvent(int eventId, T attribute, String attributeName) {
        Event event = findEventById(eventId);

        switch (attributeName) {
            case "Nome" -> event.setName((String) attribute);
            case "Horário" -> event.setDateTime((LocalDateTime) attribute);
            case "Local" -> event.setLocation((String) attribute);
            case "Capacidade" -> event.setCapacity((Integer) attribute);
            default -> System.out.println(attribute);
        }

        System.out.println(attributeName + " atualizado com sucesso!");
    }

    /**
     * Remove um evento do repositório
     *
     * @param event evento que será removido.
     */
    public void deleteEvent(Event event) {
        System.out.println("Participante '" + event.getName() + "' deletado com sucesso!");
        events.remove(event);
    }

    /**
     * Busca um evento pelo seu identificador.
     *
     * @param eventId identificador do evento.
     * @return evento correspondente ao identificador informado.
     * @throws EventNotFoundException caso o evento não seja encontrado.
     */
    public Event findEventById(int eventId) throws EventNotFoundException {
        if (events.isEmpty()) {
            throw new EventNotFoundException("Nenhum evento cadastrado anteriormente.");
        }

        int start = 0;
        int end = events.toArray().length - 1;

        while (start <= end) {
            int middle = (start + end) / 2;

            if (events.get(middle).getEventId() == eventId) {
                return events.get(middle);
            } else if (events.get(middle).getEventId() < eventId) {
                start = middle + 1;
            } else {
                end = middle - 1;
            }
        }

        return null;
    }

    /**
     * Busca um evento pelo seu nome.
     *
     * @param name nome do evento.
     * @return evento correspondente ao nome informado.
     * @throws EventNotFoundException caso o evento não seja encontrado.
     */
    public Event findEventByName(String name) throws EventNotFoundException {
        if (events.isEmpty()) {
            throw new IndexOutOfBoundsException("Nenhum evento cadastrado anteriormente.");
        }

        for (Event event : events) {
            if (event.getName().equalsIgnoreCase(name)) {
                return event;
            }
        }

        return null;
    }

}
