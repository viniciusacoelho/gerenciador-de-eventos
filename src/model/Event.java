package model;

public class Event {

    private String name;

    private String date;

    private String location;

    private int capacity;

    public Event(String name, String date, String location, int capacity) {
        this.name = name;
        this.date = date;
        this.location = location;
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

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
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
                "\nLocal: " + this.getLocation() +
                "\nCapacidade: " + this.getCapacity();
    }
}