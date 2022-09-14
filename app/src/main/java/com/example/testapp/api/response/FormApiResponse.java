package com.example.testapp.api.response;

import java.util.List;

public class FormApiResponse {
    private List<TextApiResponse> text;
    private List<ButtonApiResponse> buttons;

    public List<TextApiResponse> getText() {
        return text;
    }

    public void setText(List<TextApiResponse> text) {
        this.text = text;
    }

    public List<ButtonApiResponse> getButtons() {
        return buttons;
    }

    public void setButtons(List<ButtonApiResponse> buttons) {
        this.buttons = buttons;
    }
}
