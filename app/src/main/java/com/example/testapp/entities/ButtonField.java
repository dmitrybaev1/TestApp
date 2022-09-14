package com.example.testapp.entities;

import android.os.Parcel;
import android.os.Parcelable;

public class ButtonField implements Parcelable {
    private ButtonFieldType type;
    private String caption;
    private String formAction;

    public ButtonField(ButtonFieldType type,String caption,String formAction){
        this.type = type;
        this.caption = caption;
        this.formAction = formAction;
    }

    protected ButtonField(Parcel in) {
        type = ButtonFieldType.values()[in.readInt()];
        caption = in.readString();
        formAction = in.readString();
    }

    public static final Creator<ButtonField> CREATOR = new Creator<ButtonField>() {
        @Override
        public ButtonField createFromParcel(Parcel in) {
            return new ButtonField(in);
        }

        @Override
        public ButtonField[] newArray(int size) {
            return new ButtonField[size];
        }
    };

    public ButtonFieldType getType() {
        return type;
    }

    public String getCaption() {
        return caption;
    }

    public String getFormAction() {
        return formAction;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(type.ordinal());
        dest.writeString(caption);
        dest.writeString(formAction);
    }
}
