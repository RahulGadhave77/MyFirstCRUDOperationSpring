package com.company.service;

import com.company.entity.Developer;
import com.company.helper.ExcelHelper;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface DeveloperService {

    //Saves a Developer to the database
    String saveDeveloper(Developer developer);

    //Get all Developer Data from the database
    List<Developer> getAllDeveloper();

    //Get  a Developer data by its unique ID
    Developer getDeveloperById(int id);

    //update Devloper
    Developer updateDeve(Developer developer, int id);

    //delete developer by Id
    String deleteDeveloper(int id);

    List<Developer> filterDataBycity(String city);

    List<Developer> filterDataByGender(String gender);

    String saveExcell(MultipartFile file);

    String getExcel(int id);

}
