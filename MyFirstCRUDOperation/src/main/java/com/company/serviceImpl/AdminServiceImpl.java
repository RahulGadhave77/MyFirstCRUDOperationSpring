package com.company.serviceImpl;

import com.company.entity.Admin;
import com.company.repository.AdminRepository;
import com.company.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository ;

    @Override
    public Admin postAdmin(Admin admin) {
        Admin postData = adminRepository.save(admin);
        return postData;
    }

    @Override
    public List<Admin> getAllAdmin() {
        List<Admin> allAdmin = adminRepository.findAll();
        return allAdmin;
    }

    @Override
    public Admin getAdminById(int id) {
        Admin getAdminById = adminRepository.findById(id).orElseThrow(()-> new RuntimeException("id not show in database"));
        return getAdminById;

    }

    @Override
    public Admin updateAdmin(Admin admin , int id) {
        Admin updataAdmin = adminRepository.findById(id).orElseThrow(()-> new RuntimeException("write valid id"));
        updataAdmin.setMobileNo(admin.getMobileNo());
        Admin saveUpdatedAdmin = adminRepository.save(updataAdmin);
        return saveUpdatedAdmin;
    }

    @Override
    public String deleteAdminById(int id) {
        adminRepository.deleteById(id);
        return "delete admin";
    }
}

