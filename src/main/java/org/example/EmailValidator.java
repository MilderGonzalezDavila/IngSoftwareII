package org.example;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator {
    private static final String EMAIL_REGEX =
            "^([_A-Za-z0-9]+[-]?)*[_A-Za-z0-9]+"
            + "((\\.[_A-Za-z0-9]+)+([-]?[_A-Za-z0-9]+)*)*@"
            + "([A-Za-z0-9]+[-]?[A-Za-z0-9]+)"
            + "+(\\.[A-Za-z0-9]+[-]?[A-Za-z0-9]+)*"
            + "(\\.[A-Za-z]{2,})$";

    // Optimización: Se compila el patrón una sola vez al cargar la clase
    private static final Pattern PATTERN = Pattern.compile(EMAIL_REGEX);

    public boolean isValid(String email) {
        if (email == null) {
            return false;
        }

        Matcher matcher = PATTERN.matcher(email);
        return matcher.matches();
    }
}
