package com.example.testapp;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.testapp.entities.ButtonField;
import com.example.testapp.entities.InterfaceConfiguration;
import com.example.testapp.entities.TextField;
import com.example.testapp.view.CustomAutoCompleteTextView;
import com.example.testapp.view.CustomButton;
import com.example.testapp.view.CustomEditText;


import java.util.HashMap;
import java.util.List;

public class InputFragment extends DialogFragment {

    public static final String TAG ="InputFragment";
    public static final String CONFIGURATION_KEY="configuration";

    private LinearLayout rootLayout;
    private TextView headerTextView;

    private int counter = -1;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_input,container,false);
        rootLayout = view.findViewById(R.id.rootLayout);
        headerTextView = view.findViewById(R.id.header);
        Bundle b = getArguments();
        createInterface(b.getParcelable(CONFIGURATION_KEY));
        return view;
    }
    private void createInterface(InterfaceConfiguration configuration){
        headerTextView.setText(configuration.getHeaderText());
        List<TextField> textFieldList = configuration.getTextList();
        for(TextField textField: textFieldList){
            createTextField(textField);
        }
        List<ButtonField> buttonFieldList = configuration.getButtonList();
        createButtonField(buttonFieldList.get(0));

    }
    private void createTextField(TextField textField){
        counter++;
        int index = counter;
        switch (textField.getType()){
            case PLAIN_TEXT:
                CustomEditText plainText = new CustomEditText(getActivity());
                plainText.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT));
                plainText.setRequired(textField.isRequired());
                plainText.setAttributeName(textField.getAttribute());
                plainText.setHint(textField.getCaption());
                rootLayout.addView(plainText);
                break;
            case AUTO_COMPLETE_TEXT_VIEW:
                CustomAutoCompleteTextView autoCompleteTextView = new CustomAutoCompleteTextView(getActivity());
                autoCompleteTextView.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT));
                autoCompleteTextView.setAttributeName(textField.getAttribute());
                autoCompleteTextView.setRequired(textField.isRequired());
                autoCompleteTextView.setHint(textField.getCaption());
                ArrayAdapter<String> adapter = new ArrayAdapter<String>
                        (getActivity(),android.R.layout.select_dialog_item, textField.getSuggestions());
                autoCompleteTextView.setThreshold(1);
                autoCompleteTextView.setAdapter(adapter);
                rootLayout.addView(autoCompleteTextView);
                break;
        }
    }
    private void createButtonField(ButtonField buttonField){
        switch (buttonField.getType()){
            case BUTTON:
                CustomButton button = new CustomButton(getActivity());
                button.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT));
                button.setText(buttonField.getCaption());
                button.setEndpoint(buttonField.getFormAction());
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        MainActivity activity = (MainActivity) getActivity();
                        if(checkForRequired()) {
                            activity.makeRequestForEmployees(button.getEndpoint(), collectAttributes());
                            dismiss();
                        }
                        else
                            Toast.makeText(getActivity(),"Заполните все обязательные поля!",Toast.LENGTH_LONG).show();
                    }
                });
                rootLayout.addView(button);
                break;
        }
    }

    private HashMap<String,String> collectAttributes(){
        HashMap<String,String> attributes = new HashMap<>();
        for(int i=1; i < rootLayout.getChildCount()-1; i++){
            View v = rootLayout.getChildAt(i);
            if(v instanceof CustomAutoCompleteTextView) {
                CustomAutoCompleteTextView view = (CustomAutoCompleteTextView) v;
                if (!view.isRequired() && view.getText().length()==0)
                    continue;
                attributes.put(view.getAttributeName(),view.getText().toString());
            }
            else if(v instanceof CustomEditText){
                CustomEditText view = (CustomEditText) v;
                if (!view.isRequired() && view.getText().length()==0)
                    continue;
                attributes.put(view.getAttributeName(),view.getText().toString());
            }
        }
        return attributes;
    }

    private boolean checkForRequired(){
        for(int i=1; i < rootLayout.getChildCount()-2; i++){
            View v = rootLayout.getChildAt(i);
            if(v instanceof CustomAutoCompleteTextView) {
                CustomAutoCompleteTextView view = (CustomAutoCompleteTextView) v;
                if (view.isRequired() && view.getText().length()==0)
                    return false;
            }
            else if(v instanceof CustomEditText) {
                CustomEditText view = (CustomEditText) v;
                if (view.isRequired() && view.getText().length() == 0)
                    return false;
            }
        }
        return true;
    }
}
