package com.example.gradingsystem.services.admin;

import com.example.gradingsystem.entities.Admin;
import com.example.gradingsystem.repositories.AdminRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    private AdminRepository adminRepository;

    public AdminServiceImpl(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public Admin saveAdmin(Admin admin) {
        return adminRepository.save(admin);
    }

    @Override
    public Admin getAdmin(int id) {
        return adminRepository.findById(id).get();
    }

    @Override
    public List<Admin> getAdminList() {
        return adminRepository.findAll();
    }

    @Override
    public Admin updateAdmin(Admin admin) {
        return adminRepository.save(admin);
    }

    @Override
    public String deleteAdmin(int id) {
        adminRepository.deleteById(id);
        return "Admin deleted " + id;
    }
}
