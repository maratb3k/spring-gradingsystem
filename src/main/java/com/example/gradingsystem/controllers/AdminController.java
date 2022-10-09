package com.example.gradingsystem.controllers;

import com.example.gradingsystem.entities.Admin;
import com.example.gradingsystem.services.admin.AdminServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admins/")
public class AdminController {

    private AdminServiceImpl adminService;

    @Autowired
    public AdminController(AdminServiceImpl adminService) {
        this.adminService = adminService;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping(path = "add/")
    public Admin addAdmin(@RequestBody Admin admin) {
        return adminService.saveAdmin(admin);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public List<Admin> findAllAdmins() {
        return adminService.getAdminList();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(path = "{id}/")
    public Admin findAdminByID(@PathVariable int id) {
        return adminService.getAdmin(id);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping(path = "edit/{id}/")
    public Admin updateAdmin(@PathVariable int id, @RequestBody Admin admin) {
        admin.setId(id);
        return adminService.updateAdmin(admin);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping(path = "remove/{id}/")
    public String deleteAdmin(@PathVariable int id) {
        return adminService.deleteAdmin(id);
    }

}
