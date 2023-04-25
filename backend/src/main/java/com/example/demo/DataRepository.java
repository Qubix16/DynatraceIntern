package com.example.demo;
import com.example.demo.entities.ExchangeRates;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public class DataRepository {

    private final ApiDataFetcher apiDataFetcher;

    public DataRepository(ApiDataFetcher apiDataFetcher) {
        this.apiDataFetcher = apiDataFetcher;
    }

    Optional<ExchangeRates> getExchangeRatesByTableAndCodeAndDate(String table, String code, String date)
    {
        return apiDataFetcher.fetchDataFromApi(ApiURL.getTopCountURL(table,code,date.toString()));
    }

    Optional<ExchangeRates>getExchangeRatesByTableAndCodeAndTopCount(String table, String code, int topCount)
    {
        return apiDataFetcher.fetchDataFromApi(ApiURL.getDateURL(table,code,String.valueOf(topCount)));
    }


}
