package com.sudagoarth.springquasar.controllers;

import com.sudagoarth.springquasar.entity.Role;
import com.sudagoarth.springquasar.entity.User;
import com.sudagoarth.springquasar.response.TheResponse;
import com.sudagoarth.springquasar.services.UserService;
import com.sudagoarth.springquasar.utils.FileUploadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Set;


@RestController
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity<Object> get() {
        // Get the users
        List<User> user = userService.get();
        // Return the response
        return TheResponse.getResponse("Users fetched successfully", HttpStatus.OK, user, 1);
    }
    @GetMapping("/users/{id}")
    public ResponseEntity<Object> getById(@PathVariable("id") Integer id) {
        // Check if the user exists
        if (!userService.isExistsById(id)) {
            // Return the response
            return TheResponse.getResponse("User not found", HttpStatus.NOT_FOUND, null, 0);
        }
        // Get the user
        User user = userService.getById(id);
        // Return the response
        return TheResponse.getResponse("User fetched successfully", HttpStatus.OK, user, 1);
    }

    @PostMapping(path = "/users")
    public ResponseEntity<Object> save (
            @RequestPart("firstName") String firstName,
            @RequestPart("lastName") String lastName,
            @RequestPart("email") String email,
            @RequestPart("password") String password,
            @RequestPart("image") MultipartFile multipartFile,
            @RequestParam("roles") Set<Role> roles
    ) throws IOException {

        // check if the email is duplicate
        if (!userService.isEmailUnique(null, email)) {
            // Return the response
            return TheResponse.getResponse("Email is duplicate", HttpStatus.BAD_REQUEST, null, 0);
        }

        // Get the file name
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        // Rename the file
        fileName = FileUploadUtils.renameFile(fileName);
        // Create the user object
        User newUser = new User(email, password, firstName, lastName, fileName);
        // Set the roles
        newUser.setRoles(roles);
        // Save the user
        User user = userService.save(newUser);
        // Create the directory where the image will be saved
        String uploadDir = "user-images/" + user.getId();
        // Check if directory exists, if not create it and clean it if it exists already
        if (FileUploadUtils.isDirExists(uploadDir)) FileUploadUtils.cleanDir(uploadDir);
        // Save the file
        FileUploadUtils.saveFile(uploadDir, fileName, multipartFile);
        // Return the response
        return TheResponse.getResponse("User saved successfully", HttpStatus.OK, user, 1);
    }

    @PutMapping(path = "/users/{id}")
    public ResponseEntity<Object> update (
            @PathVariable("id") Integer id,
            @RequestPart("firstName") String firstName,
            @RequestPart("lastName") String lastName,
            @RequestPart("email") String email,
            @RequestPart("password") String password,
            @RequestPart("image") MultipartFile multipartFile,
            @RequestParam("roles") Set<Role> roles
    ) throws IOException {
        if (userService.isExistsById(id)){
            // Get the user
            User user = userService.getById(id);
            // Get the file name
            String fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
            // Rename the file
            fileName = FileUploadUtils.renameFile(fileName);
            // Create the user object
            user.setEmail(email);
            user.setPassword(password);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setImage(fileName);
            // Set the roles
            user.setRoles(roles);
            // Save the user
            User savedUser = userService.save(user);
            // Create the directory where the image will be saved
            String uploadDir = "user-images/" + savedUser.getId();
            // Check if directory exists, if not create it and clean it if it exists already
            if (FileUploadUtils.isDirExists(uploadDir)) FileUploadUtils.cleanDir(uploadDir);
            // Save the file
            FileUploadUtils.saveFile(uploadDir, fileName, multipartFile);
            // Return the response
            return TheResponse.getResponse("User updated successfully", HttpStatus.OK, savedUser, 1);
        } else {
            return TheResponse.getResponse("User not found", HttpStatus.NOT_FOUND, null, 0);
        }
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") Integer id) throws IOException {
        if (userService.isExistsById(id)) {
            // Delete the user
            userService.delete(id);
            // Create the directory where the image will be saved
            String uploadDir = "user-images/" + id;
            // Check if directory exists, if not create it and clean it if it exists already
            if (FileUploadUtils.isDirExists(uploadDir)) FileUploadUtils.cleanDir(uploadDir);
            // Return the response
            return TheResponse.getResponse("User deleted successfully", HttpStatus.OK, null, 1);
        } else {
            return TheResponse.getResponse("User not found", HttpStatus.NOT_FOUND, null, 0);
        }
    }

}
