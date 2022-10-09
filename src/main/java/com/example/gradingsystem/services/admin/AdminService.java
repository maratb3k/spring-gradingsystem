package com.example.gradingsystem.services.admin;

import com.example.gradingsystem.entities.Admin;

import java.util.List;

public interface AdminService {
    Admin saveAdmin(Admin admin);
    Admin getAdmin(int id);
    List<Admin> getAdminList();
    Admin updateAdmin(Admin admin);
    String deleteAdmin(int id);
}
