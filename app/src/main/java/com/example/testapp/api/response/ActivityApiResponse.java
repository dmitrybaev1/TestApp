package com.example.testapp.api.response;

public class ActivityApiResponse {
    private String activity;
    private LayoutApiResponse layout;

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public LayoutApiResponse getLayout() {
        return layout;
    }

    public void setLayout(LayoutApiResponse layout) {
        this.layout = layout;
    }
}
