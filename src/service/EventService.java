package service;

public class EventService {

    public boolean validateName(String name) {
        return name.length() > 3;
    }

    public boolean validateDate(String date) {
        return date.length() == 10;
    }

    public boolean validateLocation(String location) {
        return location.length() > 3;
    }

    public boolean validateCapacity(int capacity) {
        return capacity > 0;
    }

    // TODO: Date formatter

}
