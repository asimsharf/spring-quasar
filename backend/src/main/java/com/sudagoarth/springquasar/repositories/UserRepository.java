package com.sudagoarth.springquasar.repositories;


import com.sudagoarth.springquasar.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

}
