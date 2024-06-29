package com.kosvad9.ExchangeRates.mapper;

import com.kosvad9.ExchangeRates.dto.CurrencyRateDto;
import com.kosvad9.ExchangeRates.dto.NBRBRateDto;
import com.kosvad9.ExchangeRates.entity.Rate;
import org.springframework.stereotype.Component;

@Component
public class NBRBRateDto_to_CurrencyRateDto implements Mapper<NBRBRateDto, CurrencyRateDto> {
    @Override
    public CurrencyRateDto map(NBRBRateDto object) {
        return new CurrencyRateDto(object.Date(),
                object.Cur_Abbreviation(),
                object.Cur_Scale(),
                object.Cur_Name(),
                object.Cur_OfficialRate());
    }
}
