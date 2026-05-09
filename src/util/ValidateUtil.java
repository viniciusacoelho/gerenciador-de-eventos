package util;

public class ValidateUtil {

    public boolean validateName(String name) {
        return name.matches("^\\p{L}{2,}([\\s-]\\p{L}+)*$");
    }

}
