package com.sudagoarth.springquasar.services;

import com.sudagoarth.springquasar.entity.Role;
import com.sudagoarth.springquasar.entity.User;
import com.sudagoarth.springquasar.repositories.RoleRepository;
import com.sudagoarth.springquasar.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class UserService {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private  RoleRepository roleRepository;

    public UserService(UserRepository userRepository ) {
        this.userRepository = userRepository;

    }

    public List<User> get() {
        return userRepository.findAll();
    }

    public User getById(Integer id) {
        return userRepository.findById(id).get();
    }


    public User save(User user) {
        return userRepository.save(user);
    }

    public List<Role> listRoles() {
        return (List<Role>) roleRepository.findAll();
    }

    public void delete(Integer id) {
        userRepository.deleteById(id);
    }

    public boolean isExistsById(Integer id) {
        return userRepository.existsById(id);
    }

    public boolean isEmailUnique(Integer id, String email) {
        User userByEmail = userRepository.getUserByEmail(email);

        if (userByEmail == null) return true;

        boolean isCreatingNew = (id == null);

        if (isCreatingNew) {
            return false;
        } else {
            return Objects.equals(userByEmail.getId(), id);
        }
    }
}
