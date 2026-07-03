package service;

import exceptions.TicketNotFoundException;
import model.Ticket;

import java.util.List;

public class TicketService {

    public boolean validateDescription(String description) {
        return !description.isEmpty() && description.length() <= 1000;
    }

    public boolean validatePrice(double price) {
        return price > 0;
    }

    public boolean validateQuantity(int quantity) {
        return quantity > 0;
    }

    public boolean validateBenefit(String benefit) {
        return !benefit.isEmpty() && benefit.length() <= 1000;
    }

    public void hasTicket(Ticket ticket) {
        if (ticket == null) {
            throw new TicketNotFoundException();
        }
    }

    public boolean validateBenefitId(int newBenefitId, List<String> benefits) {
        return newBenefitId > 0 && newBenefitId <= benefits.size();
    }

    public void addBenefit(List<String> benefits, String benefit) {
        benefits.add(benefit);
    }

    public void listBenefits(List<String> benefits) {
        for (String benefit : benefits) {
            System.out.println(benefit);
        }
    }

    public void editBenefit(List<String> benefits, int benefitId, String newBenefit) {
        benefits.set(benefitId, newBenefit);
    }

    public void deleteBenefit(List<String> benefits, int benefitId) {
        benefits.remove(benefitId);
    }

}
