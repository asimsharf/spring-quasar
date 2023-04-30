package com.sudagoarth.springquasar.services;

import com.sudagoarth.springquasar.entity.Role;
import com.sudagoarth.springquasar.repositories.RoleRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class RoleService {

    @Autowired
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<Role> fetchRoles() {
        return roleRepository.findAll();
    }

    public Role fetchRoleById(Integer id) {
        return roleRepository.findById(id).get();
    }

}
