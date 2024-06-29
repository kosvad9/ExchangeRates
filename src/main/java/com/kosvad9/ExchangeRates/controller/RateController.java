package com.kosvad9.ExchangeRates.controller;

import com.kosvad9.ExchangeRates.dto.RateDto;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/rates")
public class RateController {
    @GetMapping
    public Boolean loadExchangeRates(@RequestParam LocalDate date){
        return false;
    }

    @GetMapping("/{currencyCode}")
    public RateDto getCurrencyRate(@PathVariable String currencyCode,
                                   @RequestParam LocalDate date){
        return null;
    }
}
