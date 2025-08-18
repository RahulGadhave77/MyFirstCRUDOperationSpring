package com.company.controller;

import com.company.entity.Developer;
import com.company.helper.ExcelHelper;
import com.company.service.DeveloperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/developer")
public class DeveloperController {

    @Autowired
    private DeveloperService developerService;

    @PostMapping("/add")
    public ResponseEntity<String> addDeveloper(@RequestBody() Developer developer) {
        System.err.println(developer);
        String s = developerService.saveDeveloper(developer);
        return new ResponseEntity<>(s, HttpStatus.CREATED);
    }

    @GetMapping("/getAllData")
    public ResponseEntity<List<Developer>> getAllDeveloper() {
        List<Developer> developerList = developerService.getAllDeveloper();
        return new ResponseEntity<>(developerList, HttpStatus.OK);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<Developer> getDeveloperById(@PathVariable("id") int id) {
        Developer developer = developerService.getDeveloperById(id);
        return new ResponseEntity<>(developer, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Developer> updateDeve(Developer developer, int id) {
        Developer updatedDeveloper = developerService.updateDeve(developer, id);
        return new ResponseEntity<>(updatedDeveloper, HttpStatus.OK);
    }

    @DeleteMapping("/deleteById")
    public ResponseEntity<String> deleteDeveloper(int id) {
        String msg = developerService.deleteDeveloper(id);
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<Developer>> filterDataByCity(@RequestParam(required = false) String city, @RequestParam(required = false) String gender) {
        List<Developer> sordedList = new ArrayList<>();

        if (gender != null) {
            sordedList = developerService.filterDataByGender(gender);
        } else {
            sordedList = developerService.filterDataBycity(city);
        }
        return new ResponseEntity<>(sordedList, HttpStatus.OK);
    }

    @PostMapping(value = "/uploadExcel",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadExcel(@RequestParam("file") MultipartFile file) {
        if (ExcelHelper.checkExelFileOrNot(file)) {
            String msg = developerService.saveExcell(file);

            return new ResponseEntity<>(msg , HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getDeveloperShet")
    public ResponseEntity<String> getDeveloperExcel(){
        String msg = developerService.getExcel();
        return new ResponseEntity<>(msg ,HttpStatus.OK);
    }
}