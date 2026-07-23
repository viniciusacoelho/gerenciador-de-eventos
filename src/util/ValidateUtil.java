package util;

/**
 * Classe utilitária responsável por fornecer métodos de validação utilizados
 * pelo sistema.
 */
public class ValidateUtil {

    /**
     * Valida um nome de acordo com as regras definidas pelo sistema.
     *
     * @param name nome que será validado.
     * @return {@code true} se o nome for válido; caso contrário, {@code false}.
     */
    public boolean validateName(String name) {
        return name.matches("^\\p{L}{2,}([\\s-]\\p{L}+)*$");
    }

}
