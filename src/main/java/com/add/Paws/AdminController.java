package com.add.Paws;

import com.add.Paws.Utils.EntityResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Id;
import java.util.List;

@RequestMapping("/api/v1/Admin")
@RestController
@CrossOrigin
public class AdminController {
    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }
    @PostMapping("/create")
    public EntityResponse create(@RequestBody Admin admin){
        return adminService.create(admin);
    }
    @DeleteMapping("/delete")
    public EntityResponse delete(Long id){
        return adminService.delete(id);
    }
    @GetMapping("/findById")
    public EntityResponse findById(@RequestParam Long id){
        return adminService.findById(id);
    }
    @GetMapping("/getAll")
    public EntityResponse getAllAdmins(){
        return adminService.getAll();
    }
    @PutMapping("/modify")
    public EntityResponse modify(@RequestBody Admin admin){
        return adminService.modify(admin);
    }
    @PutMapping("/createBulk")
    public EntityResponse createBulk(@RequestBody List<Admin> admins){
        return adminService.createBulk(admins);
    }

}
