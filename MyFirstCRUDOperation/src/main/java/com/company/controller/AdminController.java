package com.company.controller;

import com.company.entity.Admin;
import com.company.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/addAdmin")
    public ResponseEntity<Admin> postAdmin(@RequestBody Admin admin) {
        Admin postData = adminService.postAdmin(admin);
        return new ResponseEntity<>(postData , HttpStatus.OK);
    }

    @GetMapping("/getAllAdmin")
    public ResponseEntity<List<Admin>> getAllAdmin(){
        List<Admin> allAdmin = adminService.getAllAdmin();
        return new ResponseEntity<>(allAdmin , HttpStatus.OK);
    }

    @GetMapping("/getAdminById/{id}")
    public  ResponseEntity<Admin> getAdminById(@PathVariable int id){
        Admin adminById = adminService.getAdminById(id);
        return new ResponseEntity<>(adminById, HttpStatus.OK);
    }

    @PutMapping("/updateMobile/{id}")
    public ResponseEntity<Admin> updateAdmin(@RequestBody Admin admin , @PathVariable int id){
        Admin updeatedAdmin = adminService.updateAdmin(admin, id);
        return new ResponseEntity<>(updeatedAdmin , HttpStatus.OK);
    }

    @DeleteMapping("/deleteAdmin/{id}")
    public ResponseEntity<String> deleteAdminById(@PathVariable int id){
        String msg = adminService.deleteAdminById(id);
        return new ResponseEntity<>(msg , HttpStatus.OK);
    }

}
