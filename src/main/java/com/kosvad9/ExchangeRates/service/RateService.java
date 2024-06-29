package com.kosvad9.ExchangeRates.service;

import com.kosvad9.ExchangeRates.dto.CurrencyRateDto;
import com.kosvad9.ExchangeRates.dto.NBRBRateDto;
import com.kosvad9.ExchangeRates.entity.Rate;
import com.kosvad9.ExchangeRates.mapper.Mapper;
import com.kosvad9.ExchangeRates.repository.RateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class RateService {
    private final RateRepository rateRepository;
    private final NbrbAPIService nbrbAPIService;
    private final Mapper<NBRBRateDto, Rate> nbrbRateDto_to_Rate_Mapper;
    private final Mapper<Rate, CurrencyRateDto> rate_to_CurrencyRateDto_Mapper;
    private final Mapper<NBRBRateDto, CurrencyRateDto> nbrbRateDto_to_CurrencyRateDto_Mapper;

    public void loadExchangeRates(LocalDate date){
        //Метод выполняет сохранение данных НБ РБ в базу данных
        //в базе данных установлено ограничение на два поля (дата, код валюты)
        //чтобы не было дублей записей в БД.
        //Однако я пропускаю исключение об ограничении для случаев, если в БД
        //будут ранее уже загружены курсы валют за какой-нибудь день,
        //но какие-то отдельные валюты не были загружены или были добавлены позже
        List<NBRBRateDto> rates = nbrbAPIService.getRatesByDate(date);
        rates.stream()
                .map(nbrbRateDto_to_Rate_Mapper::map)
                .forEach((rate) -> {
                    try {
                        rateRepository.save(rate);
                    } catch (DataIntegrityViolationException e) {}
                });
        rateRepository.flush();
    }

    public CurrencyRateDto getCurrencyRate(LocalDate date, String curAbbreviation){
        //метод выполняет поиск курса валюты в БД
        //а в случае отсутствия в БД данных делает запрос к API НБ РБ
        Optional<Rate> rate = rateRepository.findByDateAndCurAbbreviation(date, curAbbreviation);
        if (rate.isPresent())
            return rate.map(rate_to_CurrencyRateDto_Mapper::map).get();
        else
            return nbrbRateDto_to_CurrencyRateDto_Mapper.map(
                    nbrbAPIService.getRateByDateAndCurCode(date, curAbbreviation)
            );
    }
}
