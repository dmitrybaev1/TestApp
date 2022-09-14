package com.example.testapp.api.response;

public class ErrorApiResponse {
    private String description;
    private boolean isError;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isError() {
        return isError;
    }

    public void setError(boolean error) {
        isError = error;
    }
}
