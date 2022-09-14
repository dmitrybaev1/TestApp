package com.example.testapp;

import com.example.testapp.api.response.ButtonApiResponse;
import com.example.testapp.api.response.FormApiResponse;
import com.example.testapp.api.response.LayoutApiResponse;
import com.example.testapp.api.response.TextApiResponse;
import com.example.testapp.entities.ButtonField;
import com.example.testapp.entities.ButtonFieldType;
import com.example.testapp.entities.InterfaceConfiguration;
import com.example.testapp.entities.TextField;
import com.example.testapp.entities.TextFieldType;

public class ViewMapper {
    public InterfaceConfiguration map(LayoutApiResponse layoutApiResponse){
        InterfaceConfiguration configuration = new InterfaceConfiguration();
        configuration.setHeaderText(layoutApiResponse.getHeader());
        FormApiResponse formApiResponse = layoutApiResponse.getForm();
        for(TextApiResponse textApiResponse: formApiResponse.getText()){
            configuration.addTextField(mapTextField(textApiResponse));
        }
        for(ButtonApiResponse buttonApiResponse: formApiResponse.getButtons()){
            configuration.addButtonField(mapButton(buttonApiResponse));
        }
        return configuration;
    }

    private TextField mapTextField(TextApiResponse textApiResponse){
        TextFieldType type;
        switch (textApiResponse.getType()){
            case "auto-complete-text-view":
                type = TextFieldType.AUTO_COMPLETE_TEXT_VIEW;
                break;
            case "plain-text":
                type = TextFieldType.PLAIN_TEXT;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + textApiResponse.getType());
        }
        return new TextField(
                type,
                textApiResponse.getCaption(),
                textApiResponse.getAttribute(),
                textApiResponse.isRequired(),
                textApiResponse.getSuggestions()
                );
    }

    private ButtonField mapButton(ButtonApiResponse buttonApiResponse){
        ButtonFieldType type;
        switch (buttonApiResponse.getType()){
            case "button":
                type = ButtonFieldType.BUTTON;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + buttonApiResponse.getType());
        }
        return new ButtonField(
                type,
                buttonApiResponse.getCaption(),
                buttonApiResponse.getFormAction()
        );
    }

}
