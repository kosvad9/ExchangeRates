package com.kosvad9.ExchangeRates.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CurrencyRateDto(
        @Schema(description = "Дата, на которую запрашивается курс", example = "2024-06-29")
        LocalDate date,
        @Schema(description = "Аббревиатура иностранной валюты", example = "USD")
        String abbreviation,
        @Schema(description = "Кол-во единиц иностранной валюты", example = "1")
        Integer scale,
        @Schema(description = "Наименование иностранной валюты", example = "Доллар США")
        String name,
        @Schema(description = "Курс", example = "3.1624")
        BigDecimal officialRate
) {
}
