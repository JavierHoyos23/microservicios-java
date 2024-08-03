package com.jehc.country_service.repository;

import com.jehc.country_service.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import reactor.util.annotation.NonNull;

import java.util.List;
import java.util.Optional;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {

    @NonNull
    List<Country> findAll();

    Optional<Country> findById(@NonNull Long id);

    Optional<Country> findByCode(@NonNull String code);
}
