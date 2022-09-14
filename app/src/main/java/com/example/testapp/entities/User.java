package com.example.testapp.entities;

public class User {
    private String fullName;
    private String position;
    private int workHoursInMonth;
    private int workedOutHours;

    public User(String fullName,String position,int workHoursInMonth,int workedOutHours){
        this.fullName = fullName;
        this.position = position;
        this.workHoursInMonth = workHoursInMonth;
        this.workedOutHours = workedOutHours;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPosition() {
        return position;
    }

    public int getWorkHoursInMonth() {
        return workHoursInMonth;
    }

    public int getWorkedOutHours() {
        return workedOutHours;
    }
}
