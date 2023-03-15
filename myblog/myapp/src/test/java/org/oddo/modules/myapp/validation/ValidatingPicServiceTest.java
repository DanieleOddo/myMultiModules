package org.oddo.modules.myapp.validation;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import jakarta.validation.ConstraintViolationException;

public class ValidatingPicServiceTest {

    @Autowired
    private ValidatingPicService service;

    // @Test
    // void whenInputIsInvalid_thenThrowsException() {
    // String input = " ";
    //
    // assertThrows(ConstraintViolationException.class, () -> {
    // service.validateInput(input);
    // });
    // }

}
