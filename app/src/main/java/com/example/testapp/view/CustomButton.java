package com.example.testapp.view;

import android.content.Context;
import android.widget.Button;

import androidx.annotation.NonNull;

public class CustomButton extends androidx.appcompat.widget.AppCompatButton {

    private String endpoint;

    public CustomButton(@NonNull Context context) {
        super(context);
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }
}
