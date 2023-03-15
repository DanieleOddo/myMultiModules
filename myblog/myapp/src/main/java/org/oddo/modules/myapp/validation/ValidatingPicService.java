package org.oddo.modules.myapp.validation;

import org.oddo.modules.myapp.utils.CheckInputString;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

@Validated
@Service
@Slf4j
public class ValidatingPicService {

    public boolean isPicValid(String pic) {
        log.info("Validating PIC");
        if (isValidInput(pic)) {
            // Do a query on DB
        }
        return false;
    }

    private boolean isValidInput(String input) {
        CheckInputString check = new CheckInputString();

        if (input.isBlank() || input.isEmpty()) {
            return false;
        }

        if (check.containSpecialChar(input)) {
            return false;
        }

        return true;
    }

}
