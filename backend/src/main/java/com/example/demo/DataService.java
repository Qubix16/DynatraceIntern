package com.example.demo;

import com.example.demo.entities.ExchangeRates;
import com.example.demo.entities.MinMaxValue;
import com.example.demo.entities.Rate;
import com.example.demo.entities.RateA;
import com.example.demo.entities.RateC;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;


@Service
public class DataService {

    private final DataRepository dataRepository;

    public DataService(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    public Optional<String> getAverageExchangeRateByCodeAndDate(String code, String date)
    {

        Optional<ExchangeRates> exchangeRates = dataRepository.getExchangeRatesByTableAndCodeAndDate("a",code,date);
        if(exchangeRates.isEmpty())
            return Optional.empty();
        if(exchangeRates.get().getRates().size() != 1)
            return Optional.empty();

        RateA rate = (RateA) exchangeRates.get().getRates().get(0);
        String rateMid = Double.toString(rate.getMid());
        return Optional.ofNullable(rateMid);


    }

    public Optional<MinMaxValue> getMinMaxExchangeRateByCodeAndTopCount(String code, int topCount)
    {
        Optional<ExchangeRates> exchangeRates = dataRepository.getExchangeRatesByTableAndCodeAndTopCount("a",code,topCount);
        if(exchangeRates.isEmpty())
            return Optional.empty();

        double max = 0.0;
        double min = ((RateA) exchangeRates.get().getRates().get(0)).getMid();
        for (Rate rate: exchangeRates.get().getRates())
        {
            if(rate instanceof RateA)
            {
                if(((RateA) rate).getMid() > max)
                    max = ((RateA) rate).getMid();
                if(((RateA) rate).getMid() < min)
                    min = ((RateA) rate).getMid();
            }
        }
        MinMaxValue minMaxValue = new MinMaxValue(min,max);
        return Optional.ofNullable(minMaxValue);
    }

    public Optional<Double> getDiffBidAskByCodeAndTopCount(String code, int topCount)
    {
        Optional<ExchangeRates> exchangeRates = dataRepository.getExchangeRatesByTableAndCodeAndTopCount("c",code,topCount);
        if(exchangeRates.isEmpty())
            return Optional.empty();

        double majorDiffrence = 0.0;
        for (Rate rate: exchangeRates.get().getRates())
        {
            if(rate instanceof RateC)
            {
                if(Math.abs(((RateC) rate).getAsk() - ((RateC) rate).getBid()) > majorDiffrence)
                    majorDiffrence = Math.abs(((RateC) rate).getAsk() - ((RateC) rate).getBid());
            }
        }
        return Optional.ofNullable(majorDiffrence);
    }

}
