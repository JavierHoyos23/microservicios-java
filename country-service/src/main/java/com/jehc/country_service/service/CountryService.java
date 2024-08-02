package com.jehc.country_service.service;

import com.jehc.country_service.model.Country;
import com.jehc.country_service.repository.CountryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class CountryService {

    CountryRepository countryRepository;

    public CountryService(CountryRepository countryRepository){
        this.countryRepository = countryRepository;
    }

    public List<Country> getAmericaCountries(){
        List<Country> countryList = countryRepository.findAll();
        return countryList
                .stream()
                .filter(country -> !Objects.equals(country.getCurrency(), "EUR"))
                .toList();
    }

    public List<Country> getEuropeCountries(){
        List<Country> countryList = countryRepository.findAll();
        return countryList
                .stream()
                .filter(country -> Objects.equals(country.getCurrency(), "EUR"))
                .toList();
    }
}