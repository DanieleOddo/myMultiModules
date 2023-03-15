package org.oddo.modules.myapp.utils;

import java.util.regex.Pattern;

public class CheckInputString {

    private static final String containAnyNonWordcharacter = ".*\\W.*";
    private static final String containOnlyDigit = "\\d+";

    public boolean containOnlyNumber(String input) {
        Pattern pattern = Pattern.compile(containOnlyDigit);
        return pattern.matcher(input).matches();
    }

    public boolean containSpecialChar(String input) {
        Pattern pattern = Pattern.compile(containAnyNonWordcharacter);
        return pattern.matcher(input).matches();
    }

}
