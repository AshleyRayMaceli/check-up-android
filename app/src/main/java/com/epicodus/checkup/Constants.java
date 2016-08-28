package com.epicodus.checkup;

public class Constants {
    public static final String BETTER_DOCTOR_CONSUMER_KEY = BuildConfig.BETTER_DOCTOR_CONSUMER_KEY;
    public static final String BETTER_DOCTOR_BASE_URL = "https://api.betterdoctor.com/2016-03-01/doctors?";
    public static final String BETTER_DOCTOR_SPECIALTY_QUERY_PARAMETER = "specialty_uid";
    public static final String BETTER_DOCTOR_LOCATION_QUERY_PARAMETER = "location";
    public static final String API_KEY_QUERY_PARAMETER = "user_key";
    public static final String FIREBASE_CHILD_DOCTORS = "doctors";
    public static final String FIREBASE_CHILD_AILMENTS = "ailments";
    public static final String PREFERENCES_SPECIALTY_KEY = "specialtySpinnerSelection";
    public static final String PREFERENCES_CITY_KEY = "city";
    public static final String PREFERENCES_STATE_KEY = "stateSpinnerSelection";
}
