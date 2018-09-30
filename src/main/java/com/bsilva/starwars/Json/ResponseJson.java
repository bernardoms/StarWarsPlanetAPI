package com.bsilva.starwars.Json;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public class ResponseJson {
    private String error_code;
    private String id;
    private String description;

    public String getError_code() {
        return error_code;
    }

    public void setError_code(String error_code) {
        this.error_code = error_code;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
