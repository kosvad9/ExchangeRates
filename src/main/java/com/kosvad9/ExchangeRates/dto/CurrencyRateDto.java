package com.kosvad9.ExchangeRates.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CurrencyRateDto(
        LocalDate date,
        String abbreviation,
        Integer scale,
        String name,
        BigDecimal officialRate
) {
}
