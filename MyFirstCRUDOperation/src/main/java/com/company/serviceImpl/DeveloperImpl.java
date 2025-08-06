package com.company.serviceImpl;

import com.company.entity.Developer;
import com.company.helper.DeveloperIdGenrator;
import com.company.repository.DeveloperRepository;
import com.company.service.DeveloperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeveloperImpl implements DeveloperService {

    @Autowired
     private DeveloperRepository developerRepository;

    // Save a developer to the database
    @Override
    public String saveDeveloper(Developer developer) {

        String developerId = DeveloperIdGenrator.genrateDeveloperId(developer);
        developer.setDeveloperId(developerId);
        developerRepository.save(developer);

       // Developer saveDeveloper = developerRepository.save(developer);
        return "hii"+ developer.getfName() + " your data save succ" + developer.getDeveloperId();
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

    @Override
    public Developer updateDeve(Developer developer, int id) {
        Developer update = developerRepository.findById(id).orElseThrow(() -> new RuntimeException("enter valid id"));
       update.setfName(developer.getfName());
       update.setlName(developer.getlName());
       update.setSalary(developer.getSalary());

        Developer updatedDeveloper = developerRepository.save(update);
        return updatedDeveloper;
    }

    @Override
    public String deleteDeveloper(int id) {
        developerRepository.deleteById(id);
        return "delete successfully";
    }

    @Override
    public List<Developer> filterDataBycity(String city) {
        List<Developer> AllDeveloperList = developerRepository.findAll();

        List<Developer> filterList = getAllDeveloper().stream().filter(developer -> developer.getCity().equalsIgnoreCase(city)).collect(Collectors.toList());
        return filterList;
    }

    @Override
    public List<Developer> filterDataByGender(String gender) {
        List<Developer> AllDeveloperList = developerRepository.findAll();

        List<Developer> filterListGender = getAllDeveloper().stream().filter(developer -> developer.getGender().equalsIgnoreCase(gender)).collect(Collectors.toList());
        return filterListGender;
    }
}
