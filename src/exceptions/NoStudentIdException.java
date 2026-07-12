package exceptions;

public class NoStudentIdException extends RuntimeException {

    public NoStudentIdException() {
        super("Você não pode comprar o ingresso sem carteirinha de estudante");
    }

}
