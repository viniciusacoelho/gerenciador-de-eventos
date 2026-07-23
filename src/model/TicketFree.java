package model;

import enums.TicketType;

/**
 * Representa um ingresso gratuito de um evento, utilizando as características
 * básicas definidas pela classe {@link Ticket}.
 */
public class TicketFree extends Ticket {

    /**
     * Cria um novo ingresso gratuito com as informações fornecidas.
     *
     * @param name nome do ingresso.
     * @param description descrição do ingresso.
     * @param price preço do ingresso.
     * @param ticketType tipo do ingresso.
     */
    public TicketFree(String name, String description, double price, TicketType ticketType) {
        super(name, description, price, ticketType);
    }

        /**
         * Cria um ingresso gratuito sem inicializar seus atributos.
         */
    public TicketFree() {
    }

    /**
     * Calcula o preço final do ingresso gratuito.
     *
     * @return valor final do ingresso gratuito.
     */
    @Override
    public double calculatePrice() {
        return 0;
    }

}
