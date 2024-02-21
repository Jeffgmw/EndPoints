package com.add.Paws;

import com.add.Paws.Utils.EntityResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class AdminService {

    @Autowired
    AdminRepository adminRepository;
    public EntityResponse create(Admin admin) {
        EntityResponse entityResponse = new EntityResponse<>();
        try {
            Admin savedAdmin = adminRepository.save(admin);
            savedAdmin.setPostedBy("System");
            savedAdmin.setPostedTime(LocalDateTime.now());
            savedAdmin.setPostedFlag('Y');

            Admin admin1 = adminRepository.save(savedAdmin);
            entityResponse.setMessage("Admin created successfully");
            entityResponse.setStatusCode(HttpStatus.CREATED.value());
            entityResponse.setEntity(admin1);
        }catch (Exception e){
            log.error("Exception {}", e);
            entityResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            entityResponse.setMessage(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
            entityResponse.setEntity(null);
        }
        return entityResponse;
    }

    public EntityResponse delete(Long id) {
        EntityResponse entityResponse = new EntityResponse<>();
        try {
            Optional<Admin> getAdmin = adminRepository.findById(id);
            if(getAdmin.isPresent()){
                Admin admin = getAdmin.get();
                adminRepository.delete(admin);

                entityResponse.setMessage("Admin deleted sucessfully");
                entityResponse.setStatusCode(HttpStatus.OK.value());
                entityResponse.setEntity(null);

            }else {
                entityResponse.setMessage("Admin not found");
                entityResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
                entityResponse.setEntity(null);
            }
        }catch (Exception e){
            log.error("Exception", e);
        }
        return entityResponse;
    }

    public EntityResponse findById(Long id) {
        EntityResponse entityResponse = new EntityResponse<>();
        try {
            Optional<Admin> existingAdmin = adminRepository.findById(id);
            if(existingAdmin.isPresent()){
                entityResponse.setMessage("Admin retrieved successfully" + id);
                entityResponse.setStatusCode(HttpStatus.FOUND.value());
                entityResponse.setEntity(null);

            }else {
                entityResponse.setMessage("Admin not found");
                entityResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
                entityResponse.setEntity(null);
            }
        }catch (Exception e){
            log.error("An error has occurred while trying to retrieve a teller {}",e);
        }
        return entityResponse;
    }

    public EntityResponse getAll() {
        EntityResponse entityResponse = new EntityResponse<>();

        return entityResponse;
    }

    public EntityResponse modify(Admin admin) {
        EntityResponse entityResponse = new EntityResponse<>();

        return entityResponse;
    }

    public EntityResponse createBulk(List<Admin> admins) {
        EntityResponse entityResponse = new EntityResponse<>();

        return entityResponse;
    }
}
