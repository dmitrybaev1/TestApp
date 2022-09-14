package com.example.testapp.entities;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class TextField implements Parcelable {
    private TextFieldType type;
    private String caption;
    private String attribute;
    private boolean required;
    private List<String> suggestions;

    public TextField(TextFieldType type,String caption,String attribute,boolean required,List<String> suggestions){
        this.type = type;
        this.caption = caption;
        this.attribute = attribute;
        this.required = required;
        this.suggestions = suggestions;
    }

    protected TextField(Parcel in) {
        type = TextFieldType.values()[in.readInt()];
        caption = in.readString();
        attribute = in.readString();
        required = in.readByte() != 0;
        suggestions = in.createStringArrayList();
    }

    public static final Creator<TextField> CREATOR = new Creator<TextField>() {
        @Override
        public TextField createFromParcel(Parcel in) {
            return new TextField(in);
        }

        @Override
        public TextField[] newArray(int size) {
            return new TextField[size];
        }
    };

    public TextFieldType getType() {
        return type;
    }

    public String getCaption() {
        return caption;
    }

    public String getAttribute() {
        return attribute;
    }

    public boolean isRequired() {
        return required;
    }

    public List<String> getSuggestions() {
        return suggestions;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(type.ordinal());
        dest.writeString(caption);
        dest.writeString(attribute);
        dest.writeByte((byte) (required ? 1 : 0));
        dest.writeStringList(suggestions);
    }
}
