package util;

/**
 * Classe utilitária responsável por fornecer métodos para comparação de objetos.
 */
public class IsEqualUtil {

    /**
     * Verifica se dois objetos são iguais.
     *
     * @param itemOne primeiro objeto da comparação.
     * @param itemTwo segundo objeto da comparação.
     * @param <T> tipo dos objetos comparados.
     * @return {@code true} se os objetos forem iguais; caso contrário, {@code false}.
     */
    public <T> boolean isEqual(T itemOne, T itemTwo) {
        // Verify if need it, because already have this method created in Java class Object (it's similar, but haven't this method with Int values)
        return itemOne == itemTwo;
    }

}
