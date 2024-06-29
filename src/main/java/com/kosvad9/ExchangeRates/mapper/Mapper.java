package com.kosvad9.ExchangeRates.mapper;

public interface Mapper<F, T> {
    T map(F object);
}
