package com.company.controller;

import com.company.entity.Developer;
import com.company.service.DeveloperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/developer")
public class DeveloperController {

    @Autowired
    private DeveloperService developerService;

    @PostMapping("/add")
    public ResponseEntity<String> addDeveloper(@RequestBody Developer developer){
        System.err.println(developer);
        developerService.saveDeveloper(developer);
        return new ResponseEntity<>("Developer body" ,HttpStatus.CREATED);
    }

    @GetMapping("/getAllData")
    public ResponseEntity<List<Developer>> getAllDeveloper(){
        List<Developer> developerList = developerService.getAllDeveloper();
       return new  ResponseEntity<>(developerList , HttpStatus.OK);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<Developer> getDeveloperById(@PathVariable("id") int id){
        Developer developer=developerService.getDeveloperById(id);
        return new ResponseEntity<>(developer, HttpStatus.OK);
    }
}
