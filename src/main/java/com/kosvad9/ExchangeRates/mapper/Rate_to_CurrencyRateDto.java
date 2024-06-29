package com.kosvad9.ExchangeRates.mapper;

import com.kosvad9.ExchangeRates.dto.CurrencyRateDto;
import com.kosvad9.ExchangeRates.entity.Rate;
import org.springframework.stereotype.Component;

@Component
public class Rate_to_CurrencyRateDto implements Mapper<Rate, CurrencyRateDto> {
    @Override
    public CurrencyRateDto map(Rate object) {
        return new CurrencyRateDto(object.getDate(),
                object.getCurAbbreviation(),
                object.getCurScale(),
                object.getCurName(),
                object.getCurOfficialRate());
    }
}
