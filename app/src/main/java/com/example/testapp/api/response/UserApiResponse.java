package com.example.testapp.api.response;

public class UserApiResponse {
    private String fullName;
    private String position;
    private int workHoursInMonth;
    private int workedOutHours;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getWorkHoursInMonth() {
        return workHoursInMonth;
    }

    public void setWorkHoursInMonth(int workHoursInMonth) {
        this.workHoursInMonth = workHoursInMonth;
    }

    public int getWorkedOutHours() {
        return workedOutHours;
    }

    public void setWorkedOutHours(int workedOutHours) {
        this.workedOutHours = workedOutHours;
    }
}
