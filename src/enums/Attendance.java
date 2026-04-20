package enums;

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
