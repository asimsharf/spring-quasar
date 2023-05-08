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
        List<User> user = userService.get();
        return TheResponse.getResponse("Users fetched successfully", HttpStatus.OK, user, 1);
    }
    @GetMapping("/users/{id}")
    public ResponseEntity<Object> getById(@PathVariable("id") Integer id) {
        if (!userService.isExistsById(id)) {
            return TheResponse.getResponse("User not found", HttpStatus.NOT_FOUND, null, 0);
        }
        User user = userService.getById(id);
        return TheResponse.getResponse("User fetched successfully", HttpStatus.OK, user, 1);
    }

    @PostMapping(path = "/users")
    public ResponseEntity<Object> save (
            @RequestPart("firstName") String firstName,
            @RequestPart("lastName") String lastName,
            @RequestPart("email") String email,
            @RequestPart("password") String password,
            @RequestPart("iqama") String iqama,
            @RequestPart("phone") String phone,
            @RequestPart("gender") String gender,
            @RequestPart("birthDate") String birthDate,
            @RequestPart("image") MultipartFile multipartFile,
            @RequestParam("roles") Set<Role> roles
    ) throws IOException {
        if (!userService.isEmailUnique(email)) {
            return TheResponse.getResponse("Email is duplicate", HttpStatus.BAD_REQUEST, null, 0);
        }
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        fileName = FileUploadUtils.renameFile(fileName);
        User newUser = new User(email, password, firstName, lastName, fileName, iqama, phone, gender, birthDate);
        newUser.setRoles(roles);
        User user = userService.save(newUser);
        String uploadDir = "user-images/" + user.getId();
        if (FileUploadUtils.isDirExists(uploadDir)) FileUploadUtils.cleanDir(uploadDir);
        FileUploadUtils.saveFile(uploadDir, fileName, multipartFile);
        return TheResponse.getResponse("User saved successfully", HttpStatus.OK, user, 1);
    }

    @PutMapping(path = "/users/{id}")
    public ResponseEntity<Object> update (
            @PathVariable("id") Integer id,
            @RequestPart("firstName") String firstName,
            @RequestPart("lastName") String lastName,
            @RequestPart("email") String email,
            @RequestPart("password") String password,
            @RequestPart("iqama") String iqama,
            @RequestPart("phone") String phone,
            @RequestPart("gender") String gender,
            @RequestPart("birthDate") String birthDate,
            @RequestPart("image") MultipartFile multipartFile,
            @RequestParam("roles") Set<Role> roles
    ) throws IOException {
        if (userService.isExistsById(id)){
            User user = userService.getById(id);
            String fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
            fileName = FileUploadUtils.renameFile(fileName);
            user.setEmail(email);
            user.setPassword(password);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setImage(fileName);
            user.setIqama(iqama);
            user.setPhone(phone);
            user.setGender(gender);
            user.setBirthDate(birthDate);
            user.setRoles(roles);
            User savedUser = userService.save(user);
            String uploadDir = "user-images/" + savedUser.getId();
            if (FileUploadUtils.isDirExists(uploadDir)) FileUploadUtils.cleanDir(uploadDir);
            FileUploadUtils.saveFile(uploadDir, fileName, multipartFile);
            return TheResponse.getResponse("User updated successfully", HttpStatus.OK, savedUser, 1);
        } else {
            return TheResponse.getResponse("User not found", HttpStatus.NOT_FOUND, null, 0);
        }
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") Integer id) throws IOException {
        if (userService.isExistsById(id)) {
            userService.delete(id);
            String uploadDir = "user-images/" + id;
            if (FileUploadUtils.isDirExists(uploadDir)) FileUploadUtils.cleanDir(uploadDir);
            return TheResponse.getResponse("User deleted successfully", HttpStatus.OK, null, 1);
        } else {
            return TheResponse.getResponse("User not found", HttpStatus.NOT_FOUND, null, 0);
        }
    }

}
