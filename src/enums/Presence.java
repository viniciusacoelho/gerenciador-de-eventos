package enums;

public enum Presence {

    CONFIRMED ("Confirmada"),
    PENDING ("Pendente"),
    CANCELED ("Cancelada");

    private String presence;

    Presence(String presence) {
        this.presence = presence;
    }

    public String getPresence() {
        return presence;
    }

    public void setPresence(String presence) {
        this.presence = presence;
    }
}
