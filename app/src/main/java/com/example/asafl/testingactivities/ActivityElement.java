package com.example.asafl.testingactivities;

public class ActivityElement {
    private Class activity;
    private String name;
    public ActivityElement(Class a , String s){
        name = s;
        activity = a;
    }
    public String getName() {
        return name;
    }

    public Class getActivity() {
        return activity;
    }

}
