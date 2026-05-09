package service;

public class TicketService {

    public boolean validateDescription(String description) {
        return description.length() <= 1000 && !description.isEmpty();
    }

    public boolean validatePrice(double price) {
        return price > 0;
    }

}
