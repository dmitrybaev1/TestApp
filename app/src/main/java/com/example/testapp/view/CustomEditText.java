package com.example.testapp.view;

import android.content.Context;

import androidx.annotation.NonNull;

public class CustomEditText extends androidx.appcompat.widget.AppCompatEditText {

    private String attributeName;
    private boolean required;

    public CustomEditText(@NonNull Context context) {
        super(context);
    }

    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }
}
