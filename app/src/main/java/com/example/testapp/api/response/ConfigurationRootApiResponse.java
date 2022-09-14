package com.example.testapp.api.response;

import java.util.List;

public class ConfigurationRootApiResponse {
    private List<ActivityApiResponse> activities;

    public List<ActivityApiResponse> getActivities() {
        return activities;
    }

    public void setActivities(List<ActivityApiResponse> activities) {
        this.activities = activities;
    }
}
