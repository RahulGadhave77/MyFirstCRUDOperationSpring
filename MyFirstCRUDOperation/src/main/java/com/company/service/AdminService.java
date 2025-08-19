package com.company.service;

import com.company.entity.Admin;

import java.util.List;

public interface AdminService {

    //post data to database
    Admin postAdmin(Admin admin);

    //get all data from database
    List<Admin> getAllAdmin();

    //get data from database by Id
    Admin getAdminById(int id);

    //update mobile number
    Admin updateAdmin(Admin admin , int id);

    //delete data from database from database
    String deleteAdminById(int id);
}
