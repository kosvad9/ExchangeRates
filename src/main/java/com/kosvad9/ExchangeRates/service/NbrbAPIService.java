package com.kosvad9.ExchangeRates.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kosvad9.ExchangeRates.dto.NBRBRateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
@Service
public class NbrbAPIService {
    private final RestTemplate restTemplate;

    @Value("${nbrb.api.ratesUrl}")
    private String nbrbRatesURL;

    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public List<NBRBRateDto> getRatesByDate(LocalDate date){
        //метод получения курсов валют в виде списка
        //так как у API НБ РБ требуется параметр periodicity который может
        //принимать одно из двух значений (но не одновременно два) пришлось сделать два uri
        //и дважды делать запрос
        if (date == null) date = LocalDate.now();
        var uriBuilder = UriComponentsBuilder.fromHttpUrl(nbrbRatesURL)
                .queryParam("ondate","{ondate}")
                .queryParam("periodicity", "{periodicity}");
        List<URI> uries = new ArrayList<>();
        uries.add(
                uriBuilder
                .buildAndExpand(dateFormatter.format(date), "0")
                .toUri()
        );
        uries.add(
                uriBuilder
                .buildAndExpand(dateFormatter.format(date), "1")
                .toUri()
        );
        return uries.stream()
                .map(uri -> {
                    var responseEntity = restTemplate.getForEntity(uri, NBRBRateDto[].class);
                    if (responseEntity.getStatusCode() != HttpStatus.OK)
                        throw new RuntimeException("Не были получены данные от API НБ РБ");
                    return responseEntity.getBody();
                })
                .filter(Objects::nonNull)
                .flatMap(Stream::of)
                .collect(Collectors.toList());
    }

    public NBRBRateDto getRateByDateAndCurCode(LocalDate date, String curCode){
        //получение курса валюты по трехзначному буквенному коду
        if (date == null) date = LocalDate.now();
        var uri = UriComponentsBuilder.fromHttpUrl(nbrbRatesURL+'/'+curCode)
                .queryParam("ondate","{ondate}")
                .queryParam("parammode", "{parammode}")
                .buildAndExpand(dateFormatter.format(date), "2")
                .toUri();
        var responseEntity = restTemplate.getForEntity(uri, NBRBRateDto.class);
        if (responseEntity.getStatusCode() != HttpStatus.OK)
            throw new RuntimeException("Не были получены данные от API НБ РБ");
        return responseEntity.getBody();
    }
}
