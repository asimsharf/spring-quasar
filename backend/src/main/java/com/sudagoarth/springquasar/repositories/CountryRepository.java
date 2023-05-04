package com.sudagoarth.springquasar.repositories;

import com.sudagoarth.springquasar.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CountryRepository extends JpaRepository<Country, Integer> {

    @Query("SELECT c FROM Country c WHERE c.name = ?1")
    Country getCountryByName(String name);

}
