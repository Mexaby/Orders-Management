package bll.validators;

import java.util.Objects;

/**
 * Client input validators without database access
 *
 * @author Csabi
 */
public class ClientValidator {
    /**
     * Checks if both other validators are true
     *
     * @param name
     * @param email
     * @return
     */
    public static boolean validateClient(String name, String email) {
        if (!validateName(name))
            return false;
        else return validateEmail(email);
    }

    /**
     * Checks if name contains only letters
     *
     * @param name
     * @return
     */
    private static boolean validateName(String name) {

        if (Objects.equals(name, ""))
            return false;
        return name.matches("[ a-zA-Z]+");
    }

    /**
     * Checks if email contains @ and .
     *
     * @param email
     * @return
     */
    public static boolean validateEmail(String email) {
        return email.contains("@") && email.contains(".");
    }
}
