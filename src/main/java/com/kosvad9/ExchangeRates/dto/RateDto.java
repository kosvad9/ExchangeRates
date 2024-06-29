package com.kosvad9.ExchangeRates.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record RateDto(
        LocalDate date,
        String curAbbreviation,
        Integer curScale,
        String curName,
        BigDecimal curOfficialRate
) {
}
