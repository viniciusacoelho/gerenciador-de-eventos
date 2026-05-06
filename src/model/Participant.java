package model;

import enums.Attendance;
import util.DateTimeUtil;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Participant {

    public static int totalRegisteredParticipants = 1;

    private int participantId;

    private String name;

    private String contact;

    private String email;

    private String password;

    private LocalDateTime accountDateTimeCreation;

    private List<Event> events;

    private Map<Event, Attendance> attendanceEvents;

    private final static DateTimeUtil dateTimeUtil = new DateTimeUtil();

    public Participant(String name, String contact, String email, String password) {
        this.participantId = totalRegisteredParticipants++;
        this.name = name;
        this.contact = contact;
        this.email = email;
        this.password = password;
        this.accountDateTimeCreation = LocalDateTime.now();
        this.events = new ArrayList<>();
        this.attendanceEvents = new HashMap<>();
    }

    public Participant() {
    }

    public int getTotalRegisteredParticipants() {
        return totalRegisteredParticipants;
    }

    public int getParticipantId() {
        return participantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(Event event) {
        this.events.add(event);
        setAttendanceEvents(event, Attendance.PENDING);
    }

    public Map<Event, Attendance> getAttendanceEvents() {
        return attendanceEvents;
    }

    public void setAttendanceEvents(Event event, Attendance attendance) {
        this.attendanceEvents.put(event, attendance);
    }

    public LocalDateTime getAccountDateTimeCreation() { // TODO: Verify if it's necessary, if the user will use it in the future
        return accountDateTimeCreation;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Participant that = (Participant) o;
        return participantId == that.participantId && Objects.equals(name, that.name) && Objects.equals(contact, that.contact) && Objects.equals(email, that.email) && Objects.equals(password, that.password) && Objects.equals(accountDateTimeCreation, that.accountDateTimeCreation) && Objects.equals(events, that.events) && Objects.equals(attendanceEvents, that.attendanceEvents);
    }

    @Override
    public int hashCode() {
        return Objects.hash(participantId, name, contact, email, password, accountDateTimeCreation, events, attendanceEvents);
    }

    @Override
    public String toString() {
        return "ID: " + participantId +
                "\nNome: " + name +
                "\nContato: " + contact +
                "\nE-mail: " + email +
                "\nSenha: " + password.replace(password, "*".repeat(password.length())) +
                "\nData de Criação da Conta: " + dateTimeUtil.formatDateTime(accountDateTimeCreation);
    }

}
