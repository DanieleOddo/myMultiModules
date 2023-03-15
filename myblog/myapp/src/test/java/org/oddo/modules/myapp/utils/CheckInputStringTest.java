package org.oddo.modules.myapp.utils;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class CheckInputStringTest {

    @Test
    void containSpecialChar() {

        String input = "12asd/";

        CheckInputString check = new CheckInputString();
        assertTrue(check.containSpecialChar(input));

    }

    @Test
    void notContainSpecialChar() {

        String input = "test123";

        CheckInputString check = new CheckInputString();
        assertFalse(check.containSpecialChar(input));

    }

    @Test
    void containOnlyNumber() {

        String input = "123";

        CheckInputString check = new CheckInputString();
        assertTrue(check.containOnlyNumber(input));

    }

    @Test
    void notContainOnlyNumber() {

        String input = "asdf123";

        CheckInputString check = new CheckInputString();
        assertFalse(check.containOnlyNumber(input));

    }

}
