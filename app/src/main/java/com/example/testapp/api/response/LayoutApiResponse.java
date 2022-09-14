package com.example.testapp.api.response;

public class LayoutApiResponse {
    private String header;
    private FormApiResponse form;

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public FormApiResponse getForm() {
        return form;
    }

    public void setForm(FormApiResponse form) {
        this.form = form;
    }
}
