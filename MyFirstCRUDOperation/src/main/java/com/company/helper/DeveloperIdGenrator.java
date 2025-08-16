package com.company.helper;

import com.company.entity.Developer;

public class DeveloperIdGenrator {

    public  static String genrateDeveloperId(Developer developer){

        String fName = developer.getFName();
        String lName = developer.getLName();
        int dob = developer.getYearOfBirth();

        char name = fName.charAt(0);

        int lastTwoDigits = dob % 100;

        String genrateDeveloperId = name + lName + lastTwoDigits;

        return genrateDeveloperId;
    }
    public static String getDeveloperIdForExcel(Developer developer) {
        String fName = developer.getFName();
        String lName = developer.getLName();
        int dob = developer.getYearOfBirth();

        // ✅ Safe handling for null or empty first name
        char firstChar = (fName != null && !fName.isEmpty()) ? fName.charAt(0) : 'X';

        // ✅ Safe handling for null last name
        String safeLName = (lName != null && !lName.isEmpty()) ? lName : "Unknown";

        // ✅ Take last two digits of DOB safely
        int lastTwoDigits = (dob > 0) ? (dob % 100) : 0;

        // ✅ Build developer ID
        return firstChar + safeLName + lastTwoDigits;
    }
}
