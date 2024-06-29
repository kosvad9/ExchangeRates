package com.kosvad9.ExchangeRates.mapper;

import com.kosvad9.ExchangeRates.dto.NBRBRateDto;
import com.kosvad9.ExchangeRates.entity.Rate;
import org.springframework.stereotype.Component;

@Component
public class NBRBRateDto_to_Rate implements Mapper<NBRBRateDto, Rate> {
    @Override
    public Rate map(NBRBRateDto object) {
        return Rate.builder()
                .date(object.Date())
                .curAbbreviation(object.Cur_Abbreviation())
                .curScale(object.Cur_Scale())
                .curName(object.Cur_Name())
                .curOfficialRate(object.Cur_OfficialRate())
                .build();
    }
}
