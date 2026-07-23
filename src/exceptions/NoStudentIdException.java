package exceptions;

/**
 * Exceção lançada quando um participante tenta adquirir um ingresso de meia-entrada sem possuir uma carteira de estudante válida.
 */
public class NoStudentIdException extends RuntimeException {

    public NoStudentIdException() {
        super("Você não pode comprar o ingresso sem carteirinha de estudante");
    }

}
