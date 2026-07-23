package enums;

/**
 * Representa os possíveis estados de um evento.
 */
public enum Status {

    AVAILABLE ("Disponível"),
    SOLD_OUT ("Esgotado");

    private final String status;

    Status(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

}
