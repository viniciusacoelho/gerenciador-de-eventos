package model;

public class Event {

    private String name;

    private String date;

    private String place;

    private int capacity;

    public Event(String name, String date, String place, int capacity) {
        this.name = name;
        this.date = date;
        this.place = place;
        this.capacity = capacity;
    }

    public Event() {

    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPlace() {
        return this.place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public int getCapacity() {
        return this.capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return "Nome: " + this.getName() +
                "\nData: " + this.getDate() +
                "\nLocal: " + this.getPlace() +
                "\nCapacidade: " + this.getCapacity();
    }
}