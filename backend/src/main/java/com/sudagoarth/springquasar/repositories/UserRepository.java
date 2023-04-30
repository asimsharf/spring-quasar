package com.sudagoarth.springquasar.repositories;

import com.sudagoarth.springquasar.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("SELECT u FROM User u WHERE u.email = ?1")
    User getUserByEmail(String email);

}
