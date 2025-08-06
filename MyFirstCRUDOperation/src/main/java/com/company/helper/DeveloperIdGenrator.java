package com.company.helper;

import com.company.entity.Developer;

public class DeveloperIdGenrator {

    public  static String genrateDeveloperId(Developer developer){

        String fName = developer.getfName();
        String lName = developer.getlName();
        int dob = developer.getYearOfBirth();

        char name = fName.charAt(0);

        int lastTwoDigits = dob % 100;

        String genrateDeveloperId = name + lName + lastTwoDigits;

        return genrateDeveloperId;

    }
}
