package model;

import enums.TicketType;

/**
 * Representa um ingresso padrão de um evento, utilizando as características
 * básicas definidas pela classe {@link Ticket}.
 */
public class TicketDefault extends Ticket {

    /**
     * Cria um novo ingresso padrão com as informações fornecidas.
     *
     * @param name nome do ingresso.
     * @param description descrição do ingresso.
     * @param price preço do ingresso.
     * @param ticketType tipo do ingresso.
     */
    public TicketDefault(String name, String description, double price, TicketType ticketType) {
        super(name, description, price, ticketType);
    }

    /**
     * Cria um ingresso padrão sem inicializar seus atributos.
     */
    public TicketDefault() {
    }

    /**
     * Calcula o preço final do ingresso padrão.
     *
     * @return preço final do ingresso padrão.
     */
    @Override
    public double calculatePrice() {
        return getPrice();
    }

}
