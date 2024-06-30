package com.kosvad9.ExchangeRates.controller;

import com.kosvad9.ExchangeRates.dto.CurrencyRateDto;
import com.kosvad9.ExchangeRates.service.RateService;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/rates")
public class RateController {
    private final RateService rateService;
    @GetMapping
    public ResponseEntity<String> loadExchangeRates(@RequestParam @Nullable
                                                    @Parameter(description = "Дата, на которую запрашивается курс",
                                                                example = "2024-06-29") LocalDate date){
        rateService.loadExchangeRates(date);
        return new ResponseEntity<>("Успешно загружено!", HttpStatus.OK);
    }

    @GetMapping("/{curAbbreviation}")
    public CurrencyRateDto getCurrencyRate(@PathVariable
                                           @Parameter(description = "Код валюты (3 символа)",
                                                        example = "USD") String curAbbreviation,
                                           @RequestParam @Nullable
                                           @Parameter(description = "Дата, на которую запрашивается курс",
                                                        example = "2024-06-29") LocalDate date){
        return rateService.getCurrencyRate(date, curAbbreviation);
    }
}
