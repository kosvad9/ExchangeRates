package com.kosvad9.ExchangeRates.repository;

import com.kosvad9.ExchangeRates.entity.Rate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface RateRepository extends JpaRepository<Rate, Long> {
    Optional<Rate> findByDateAndCurAbbreviationIgnoreCase(LocalDate date, String curAbbreviation);
}
