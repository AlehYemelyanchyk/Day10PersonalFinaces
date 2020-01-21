package by.javatr.finances.service.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Aleh Yemelyanchyk on 12/27/2019.
 */
public final class ServiceValidation {

    private final static String PASSWORD_REGEX = "^(?=.*[A-Za-z])(?=.*\\d)[a-zA-Z\\d]{8,}$";
    private final static String EMAIL_REGEX = "^[a-zA-Z0-9.!#$%&'*+" +
            "\\/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:" +
            "\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$";

    protected ServiceValidation() {
    }

    public static boolean isLoginValid(String login) {
        return login != null && !login.isEmpty();
    }

    public static boolean isPasswordValid(String password) {
        if (password == null) {
            return false;
        }
        Pattern pattern = Pattern.compile(PASSWORD_REGEX);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    public static boolean isEmailValid(String email) {
        if (email == null) {
            return false;
        }
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
