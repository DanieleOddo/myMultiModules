package org.oddo.modules.myapp.exception;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ApiFaultResponse {
    private String type;
    private String title;
    private HttpStatus status;
    private String detail;
    private String instance;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private final Map<String, Object> data = new LinkedHashMap<>();

    public ApiFaultResponse(HttpStatus status) {
        this.status = status;
    }

    public ApiFaultResponse(HttpStatus status, String type, String title) {
        this.type = type;
        this.title = title;
        this.status = status;
    }

    public Object put(String s, Object o) {
        return data.put(s, o);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getInstance() {
        return instance;
    }

    public void setInstance(String instance) {
        this.instance = instance;
    }

    public Map<String, Object> getData() {
        return data;
    }
}
