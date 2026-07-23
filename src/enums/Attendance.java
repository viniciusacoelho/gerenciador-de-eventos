package enums;

/**
 * Representa os possíveis estados de presença de um participante em um evento.
 */
public enum Attendance {

    CONFIRMED ("Confirmada"),
    PENDING ("Pendente"),
    CANCELED ("Cancelada");

    private final String attendance;

    Attendance(String attendance) {
        this.attendance = attendance;
    }

    public String getAttendance() {
        return attendance;
    }

}
