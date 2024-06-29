package com.kosvad9.ExchangeRates.repository;

import com.kosvad9.ExchangeRates.entity.Rate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface RateRepository extends JpaRepository<Rate, Long> {
    Optional<Rate> findByDateAndCurAbbreviation(LocalDate date, String curAbbreviation);
}
