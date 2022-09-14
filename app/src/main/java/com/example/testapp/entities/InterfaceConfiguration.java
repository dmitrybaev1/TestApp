package com.example.testapp.entities;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class InterfaceConfiguration implements Parcelable {
    private String headerText;
    private ArrayList<TextField> textList = new ArrayList<>();
    private ArrayList<ButtonField> buttonList = new ArrayList<>();

    public InterfaceConfiguration(){}
    protected InterfaceConfiguration(Parcel in) {
        headerText = in.readString();
        textList = in.createTypedArrayList(TextField.CREATOR);
        buttonList = in.createTypedArrayList(ButtonField.CREATOR);
    }

    public static final Creator<InterfaceConfiguration> CREATOR = new Creator<InterfaceConfiguration>() {
        @Override
        public InterfaceConfiguration createFromParcel(Parcel in) {
            return new InterfaceConfiguration(in);
        }

        @Override
        public InterfaceConfiguration[] newArray(int size) {
            return new InterfaceConfiguration[size];
        }
    };

    public void setHeaderText(String headerText){
        this.headerText = headerText;
    }

    public String getHeaderText(){
        return headerText;
    }

    public void addTextField(TextField textField){
        textList.add(textField);
    }

    public void addButtonField(ButtonField buttonField){
        buttonList.add(buttonField);
    }

    public ArrayList<TextField> getTextList(){
        return textList;
    }

    public ArrayList<ButtonField> getButtonList(){
        return buttonList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(headerText);
        dest.writeTypedList(textList);
        dest.writeTypedList(buttonList);
    }
}
