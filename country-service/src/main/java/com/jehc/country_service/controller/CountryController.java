package com.jehc.country_service.controller;

import com.jehc.country_service.model.Country;
import com.jehc.country_service.repository.CountryRepository;
import com.jehc.country_service.service.CountryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CountryController {

    CountryRepository countryRepository;

    CountryService countryService;

    public CountryController(CountryRepository countryRepository, CountryService countryService) {
        this.countryRepository = countryRepository;
        this.countryService = countryService;
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