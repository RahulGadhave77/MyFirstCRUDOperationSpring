package com.company.serviceImpl;

import com.company.entity.Developer;
import com.company.repository.DeveloperRepository;
import com.company.service.DeveloperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeveloperImpl implements DeveloperService {

    @Autowired
     private DeveloperRepository developerRepository;

    // Save a developer to the database
    @Override
    public String saveDeveloper(Developer developer) {
        Developer saveDeveloper = developerRepository.save(developer);
        return "devloper save";
    }

    // Get all developers from the database
    @Override
    public List<Developer> getAllDeveloper() {
        List<Developer> developerList = developerRepository.findAll();
        return developerList;
    }

    // Retrieve a single developer by ID
    @Override
    public Developer getDeveloperById(int id) {
        Developer developer
                =developerRepository.findById(id).orElseThrow(()->new NullPointerException("Developer with id not fount" + id));
        return developer;
    }
}
