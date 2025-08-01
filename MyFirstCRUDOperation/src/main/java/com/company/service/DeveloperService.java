package com.company.service;

import com.company.entity.Developer;

import java.util.List;

public interface DeveloperService {

    //Saves a Developer to the database
    String saveDeveloper(Developer developer);

    //Get all Developer Data from the database
    List<Developer> getAllDeveloper();

    //Get  a Developer data by its unique ID
    Developer getDeveloperById(int id);
}
