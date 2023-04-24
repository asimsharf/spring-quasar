package com.sudagoarth.springquasar.services;

import com.sudagoarth.springquasar.entity.User;
import com.sudagoarth.springquasar.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> fetchUsers() {
        return userRepository.findAll();
    }

}
