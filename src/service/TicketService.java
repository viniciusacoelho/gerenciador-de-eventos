package service;

import exceptions.TicketNotFoundException;
import model.Ticket;

import java.util.List;

/**
 * Responsável por fornecer os serviços relacionados ao gerenciamento de
 * ingressos, realizando operações de cadastro, consulta, validação,
 * atualização e remoção.
 */
public class TicketService {

    /**
     * Valida a descrição de um ingresso de acordo com as regras definidas pelo sistema.
     *
     * @param description descrição do ingresso.
     * @return {@code true} se a descrição for válida; caso contrário, {@code false}.
     */
    public boolean validateDescription(String description) {
        return !description.isEmpty() && description.length() <= 1000;
    }

    /**
     * Valida o preço de um ingresso de acordo com as regras definidas pelo sistema.
     *
     * @param price preço do ingresso.
     * @return {@code true} se o preço for válido; caso contrário, {@code false}.
     */
    public boolean validatePrice(double price) {
        return price > 0;
    }

    /**
     * Valida a quantidade de ingressos de acordo com as regras definidas pelo sistema.
     *
     * @param quantity quantidade de ingressos.
     * @return {@code true} se a quantidade for válida; caso contrário, {@code false}.
     */
    public boolean validateQuantity(int quantity) {
        return quantity > 0;
    }

    /**
     * Valida um benefício de um ingresso VIP de acordo com as regras definidas pelo sistema.
     *
     * @param benefit benefício do ingresso VIP.
     * @return {@code true} se o benefício for válido; caso contrário, {@code false}.
     */
    public boolean validateBenefit(String benefit) {
        return !benefit.isEmpty() && benefit.length() <= 1000;
    }

    /**
     * Verifica se um ingresso existe no sistema.
     *
     * @param ticket ingresso que será verificado.
     * @throws TicketNotFoundException caso o ingresso não exista.
     */
    public void hasTicket(Ticket ticket) throws TicketNotFoundException {
        if (ticket == null) {
            throw new TicketNotFoundException();
        }
    }

    /**
     * Verifica se um ingresso está cadastrado no sistema.
     *
     * @param ticket ingresso que será verificado.
     * @return {@code true} se o ingresso estiver cadastrado; caso contrário, {@code false}.
     */
    public boolean hasTicketRegistered(Ticket ticket) {
        return ticket.getTotalRegisteredTickets() == 0;
    }

    /**
     * Valida o identificador de um benefício de um ingresso VIP.
     *
     * @param newBenefitId identificador do benefício.
     * @param benefits lista de benefícios do ingresso VIP.
     * @return {@code true} se o identificador for válido; caso contrário, {@code false}.
     */
    public boolean validateBenefitId(int newBenefitId, List<String> benefits) {
        return newBenefitId > 0 && newBenefitId <= benefits.size();
    }

    /**
     * Adiciona um benefício à lista de benefícios de um ingresso VIP.
     *
     * @param benefits lista de benefícios do ingresso VIP.
     * @param benefit benefício que será adicionado.
     */
    public void addBenefit(List<String> benefits, String benefit) {
        benefits.add(benefit);
    }

    /**
     * Exibe a lista de benefícios de um ingresso VIP.
     *
     * @param benefits lista de benefícios do ingresso VIP.
     */
    public void listBenefits(List<String> benefits) {
        for (String benefit : benefits) {
            System.out.println(benefit);
        }
    }

    /**
     * Edita um benefício da lista de benefícios de um ingresso VIP.
     *
     * @param benefits lista de benefícios do ingresso VIP.
     * @param benefitId identificador do benefício que será editado.
     * @param newBenefit novo benefício que substituirá o atual.
     */
    public void editBenefit(List<String> benefits, int benefitId, String newBenefit) {
        benefits.set(benefitId, newBenefit);
    }

    /**
     * Remove um benefício da lista de benefícios de um ingresso VIP.
     *
     * @param benefits lista de benefícios do ingresso VIP.
     * @param benefitId identificador do benefício que será removido.
     */
    public void deleteBenefit(List<String> benefits, int benefitId) {
        benefits.remove(benefitId);
    }

}
