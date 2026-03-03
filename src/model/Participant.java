package model;

import enums.Presence;

public class Participant {

    private String name;

    private int contact;

    private Presence presence;

    public Participant(String name, int contact) {
        this.name = name;
        this.contact = contact;
        this.presence = Presence.PENDING;
    }

    public Participant() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getContact() {
        return contact;
    }

    public void setContact(int contact) {
        this.contact = contact;
    }

    @Override
    public String toString() {
        return "Nome: " + this.name + "\nContato:" + this.contact;
    }
}
