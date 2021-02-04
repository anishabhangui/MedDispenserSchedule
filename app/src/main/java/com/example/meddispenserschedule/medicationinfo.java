package com.example.meddispenserschedule;

public class medicationinfo {
    private String mMedName;
    private String mTime;
    private String mNumbPills;

    public medicationinfo(String nameofmed, String timeformed, String numberpills ) {
        mMedName = nameofmed;
        mTime = timeformed;
        mNumbPills = numberpills;
    }

    public String getmMedName() {
        return mMedName;
    }

    public String getMedTime() {
        return mTime;
    }

    public String getNumbPills() {
        return mNumbPills;
    }
}
