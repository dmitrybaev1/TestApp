package com.example.testapp.api.response;

public class UserRootApiResponse {
    private DataApiResponse data;
    private ErrorApiResponse error;

    public DataApiResponse getData() {
        return data;
    }

    public void setData(DataApiResponse data) {
        this.data = data;
    }

    public ErrorApiResponse getError() {
        return error;
    }

    public void setError(ErrorApiResponse error) {
        this.error = error;
    }
}
