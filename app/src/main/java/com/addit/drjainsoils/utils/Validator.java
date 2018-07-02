package com.addit.drjainsoils.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    public static boolean isValidEmail(String email) {
        Pattern emailPattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        Matcher emailMatcher = emailPattern.matcher(email);
        return emailMatcher.find();
    }

    public static String capitalizeFirstLetter(String str) {
        return str == null || str.length() == 0 ? str : str.substring(0, 1).toUpperCase() + str.substring(1);
    }
}
