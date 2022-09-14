package com.example.testapp.view;

import android.content.Context;

import androidx.annotation.NonNull;

public class CustomAutoCompleteTextView extends androidx.appcompat.widget.AppCompatAutoCompleteTextView {

    private String attributeName;
    private boolean required;

    public CustomAutoCompleteTextView(@NonNull Context context) {
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
