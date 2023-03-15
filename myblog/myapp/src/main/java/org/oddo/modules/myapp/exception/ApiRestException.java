package org.oddo.modules.myapp.exception;

import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import java.util.Map;
import java.util.LinkedHashMap;

public class ApiRestException extends ResponseStatusException {

    public String code;
    public String message;
    private final Map<String, Object> contextDetails = new LinkedHashMap<>();

    public ApiRestException(HttpStatus status) {
        super(status);
    }

    public static ApiRestException instance(HttpStatus type) {
        return new ApiRestException(type);
    }

    public ApiRestException code(String code) {
        this.code = code;
        return this;
    }

    public ApiRestException message(String message) {
        this.message = message;
        return this;
    }

    public ApiRestException withContextDetail(String key, Object detail) {
        this.contextDetails.put(key, detail);
        return this;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public Map<String, Object> getContextDetails() {
        return contextDetails;
    }

}
