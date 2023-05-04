package com.sudagoarth.springquasar.services;


import com.sudagoarth.springquasar.entity.Country;
import com.sudagoarth.springquasar.repositories.CountryRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class CountryService {

    @Autowired
    private final CountryRepository countryRepository;

    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public List<Country> get() {
        return countryRepository.findAll();
    }

    public Country getById(Integer id) {
        return countryRepository.findById(id).get();
    }

    public  Country save(Country country) {
        return countryRepository.save(country);
    }

    public void delete(Integer id) {
        countryRepository.deleteById(id);
    }

    public boolean isExistsById(Integer id) {
        return countryRepository.existsById(id);
    }

    public boolean isNameUnique(Integer id, String name) {
        Country countryByName = countryRepository.getCountryByName(name);
        if (countryByName == null) return true;
        boolean isCreatingNew = (id == null);
        return !isCreatingNew && Objects.equals(countryByName.getId(), id);
    }


}
