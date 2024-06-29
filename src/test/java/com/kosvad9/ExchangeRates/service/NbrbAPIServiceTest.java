package com.kosvad9.ExchangeRates.service;

import com.kosvad9.ExchangeRates.dto.NBRBRateDto;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class NbrbAPIServiceTest {
    @Autowired
    NbrbAPIService nbrbAPIService;

    @Test
    void getRatesByDateShouldReturnExchangeRates(){
        List<NBRBRateDto> list = nbrbAPIService.getRatesByDate(LocalDate.now());
        Assertions.assertThat(list).isNotEmpty();
        list.forEach(System.out::println);
    }

    @Test
    void getRateByDateAndCurCodeShouldRateWhenCurCodeIsValid(){
        NBRBRateDto nbrbRateDto = nbrbAPIService.getRateByDateAndCurCode(LocalDate.now(), "USD");
        Assertions.assertThat(nbrbRateDto).isNotNull();
        System.out.println(nbrbRateDto);
    }

    @Test
    void getRateByDateAndCurCodeShouldRateWhenCurCodeIsNotValid(){
        Assertions.assertThatThrownBy(() -> nbrbAPIService.getRateByDateAndCurCode(LocalDate.now(), "BBB"));
    }
}