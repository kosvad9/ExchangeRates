package com.kosvad9.ExchangeRates.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record NBRBRateDto(
        Integer Cur_ID,
        LocalDate Date,
        String Cur_Abbreviation,
        Integer Cur_Scale,
        String Cur_Name,
        BigDecimal Cur_OfficialRate
) {
}
