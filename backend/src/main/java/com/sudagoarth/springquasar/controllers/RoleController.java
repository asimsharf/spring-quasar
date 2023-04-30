package com.sudagoarth.springquasar.controllers;

import com.sudagoarth.springquasar.repositories.RoleRepository;
import com.sudagoarth.springquasar.response.TheResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class RoleController {

    @Autowired
    private final RoleRepository roleRepository;

    public RoleController(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @GetMapping("/roles")
    public ResponseEntity<Object> fetchRoles() {
        return TheResponse.getResponse("Roles fetched successfully", HttpStatus.OK, roleRepository.findAll(), 200);
    }

    @GetMapping("/roles/{id}")
    public ResponseEntity<Object> fetchRoleById(@PathVariable("id") Integer id) {
        return TheResponse.getResponse("Role fetched successfully", HttpStatus.OK, roleRepository.findById(id).get(), 200);
    }

}
