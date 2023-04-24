package com.sudagoarth.springquasar.controllers;


import com.sudagoarth.springquasar.response.TheResponse;
import com.sudagoarth.springquasar.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public ResponseEntity<Object> fetchUsers() {
        return TheResponse.getResponse("Users fetched successfully", org.springframework.http.HttpStatus.OK, userService.fetchUsers(), 200);
    }

}
