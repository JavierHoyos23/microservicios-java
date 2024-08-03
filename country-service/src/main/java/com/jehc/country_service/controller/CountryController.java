package com.jehc.country_service.controller;

import com.jehc.country_service.model.Country;
import com.jehc.country_service.repository.CountryRepository;
import com.jehc.country_service.service.CountryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CountryController {

    CountryRepository countryRepository;

    CountryService countryService;

    public CountryController(CountryRepository countryRepository, CountryService countryService) {
        this.countryRepository = countryRepository;
        this.countryService = countryService;
    }

    @PostMapping("/create")
    public ResponseEntity<Country> createCountry(@RequestBody Country country) {
        Optional<Country> countryToCreate = countryRepository.findByCode(country.getCode());
        if (countryToCreate.isEmpty()) {
            return new ResponseEntity<>(countryRepository.save(country), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<Country> updateCountry(@RequestBody Country country) {
        Optional<Country> optCountry = countryRepository.findById(country.getId());
        if (optCountry.isPresent()) {
            Country countryToUpdate = optCountry.get();
            countryToUpdate.setName(country.getName());
            countryToUpdate.setCode(country.getCode());
            countryToUpdate.setCurrency(country.getCurrency());
            return new ResponseEntity<>(countryRepository.save(countryToUpdate), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Country> deleteCountry(@RequestBody Country country) {
        Optional<Country> optCountry = countryRepository.findById(country.getId());
        if (optCountry.isPresent()) {
            countryRepository.delete(country);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getAll")
    public List<Country> getAllCountries(){
        return countryRepository.findAll();
    }

    @GetMapping("/america")
    public List<Country> getAmericaCountries(){
        return countryService.getAmericaCountries();
    }

    @GetMapping("/europe")
    public List<Country> getEuropeCountries(){
        return countryService.getEuropeCountries();
    }
}