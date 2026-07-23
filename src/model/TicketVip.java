package model;

import enums.TicketType;
import service.TicketService;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa um ingresso VIP de um evento, contendo funcionalidades adicionais
 * e benefícios exclusivos, além das características básicas definidas pela classe {@link Ticket}.
 */
public class TicketVip extends Ticket {

    private List<String> benefits = new ArrayList<>();

    private final TicketService ticketService = new TicketService();

    /**
     * Cria um novo ingresso VIP com as informações fornecidas.
     *
     * @param name nome do ingresso.
     * @param description descrição do ingresso.
     * @param price preço do ingresso.
     * @param ticketType tipo do ingresso.
     * @param benefits lista de benefícios exclusivos do ingresso VIP.
     */
    public TicketVip(String name, String description, double price, TicketType ticketType, List<String> benefits) {
        super(name, description, price, ticketType);
        this.benefits = benefits;
    }

    /**
     * Cria um ingresso VIP sem inicializar seus atributos.
     */
    public TicketVip() {
    }

    /**
     * Retorna a lista de benefícios exclusivos do ingresso VIP.
     *
     * @return lista de benefícios do ingresso VIP.
     */
    public List<String> getBenefits() {
        return benefits;
    }

    /**
     * Define a lista de benefícios exclusivos do ingresso VIP.
     *
     * @param benefits nova lista de benefícios do ingresso VIP.
     */
    public void setBenefits(List<String> benefits) {
        this.benefits = benefits;
    }

    /**
     * Calcula o preço final do ingresso VIP considerando suas características específicas.
     *
     * @return preço final do ingresso VIP.
     */
    @Override
    public double calculatePrice() {
//        return getPrice() * 1.5;
        return getPrice() * 2;
    }

    /**
     * Retorna uma representação textual das informações do ingresso VIP, incluindo * seus benefícios exclusivos.
     * @return representação em formato de texto do ingresso VIP.
     */
    @Override
    public String toString() {
        return "ID: " + super.getTicketId() +
                "\nNome: " + super.getName() +
                "\nDescrição: " + super.getDescription() +
                "\nPreço: R$ " + super.getPrice() +
                "\nBenefícios:";
    }

}
