package model;

import enums.TicketType;

/**
 * Representa um ingresso de meia-entrada de um evento, contendo as regras
 * específicas para cálculo de preço e validação de carteira de estudante,
 * além das características básicas definidas pela classe {@link Ticket}.
 */
public class TicketHalfPrice extends Ticket {

    private boolean studentId;

    /**
     * Cria um novo ingresso de meia-entrada com as informações fornecidas.
     *
     * @param name nome do ingresso.
     * @param description descrição do ingresso.
     * @param price preço do ingresso.
     * @param ticketType tipo do ingresso.
     * @param studentId indica se o participante possui carteira de estudante válida.
     */
    public TicketHalfPrice(String name, String description, double price, TicketType ticketType, boolean studentId) {
        super(name, description, price, ticketType);
        this.studentId = studentId;
    }

    /**
     * Cria um ingresso de meia-entrada sem inicializar seus atributos.
     */
    public TicketHalfPrice() {
    }

    /**
     * Verifica se o ingresso de meia-entrada possui uma carteira de estudante associada.
     *
     * @return true se possuir carteira de estudante; caso contrário, false.
     */
    public boolean hasStudentId() {
        return studentId;
    }

    /**
     * Define se o ingresso de meia-entrada possui uma carteira de estudante associada.
     *
     * @param studentId indica se o participante possui carteira de estudante válida.
     */
    public void setStudentId(boolean studentId) {
        this.studentId = studentId;
    }

    /**
     * /** * Calcula o preço final do ingresso de meia-entrada aplicando a regra de desconto.
     *
     * @return preço final do ingresso de meia-entrada.
     */
    @Override
    public double calculatePrice() {
        return hasStudentId() ? getPrice() / 2 : -1;
    }

    /**
     * Retorna uma representação textual das informações do ingresso de meia-entrada.
     *
     * @return representação em formato de texto do ingresso de meia-entrada.
     */
    @Override
    public String toString() {
        return "ID: " + super.getTicketId() +
                "\nNome: " + super.getName() +
                "\nDescrição: " + super.getDescription() +
                "\nPreço: R$ " + super.getPrice();
    }

}