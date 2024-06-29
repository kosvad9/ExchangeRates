package com.kosvad9.ExchangeRates.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RateServiceTest {
    @Autowired
    private RateService rateService;

    @Test
    void loadExchangeRatesShouldNotThrowsConstraintException(){
        rateService.loadExchangeRates(LocalDate.now());
        rateService.loadExchangeRates(LocalDate.now());
    }
}