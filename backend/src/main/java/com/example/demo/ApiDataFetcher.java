package com.example.demo;

import com.example.demo.entities.ExchangeRates;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Optional;

@Component
public class ApiDataFetcher {

    public Optional<ExchangeRates> fetchDataFromApi(String url) {

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

        try {
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

            ObjectMapper mapper = new ObjectMapper();
            ExchangeRates exchangeRates = mapper.readValue(response.getBody(), ExchangeRates.class);
            return Optional.ofNullable(exchangeRates);

        } catch (JsonProcessingException e)
        {
            throw new RuntimeException("Error deserializing JSON string: " + e.getMessage());
        }
        catch(HttpClientErrorException.NotFound e)
        {
            return Optional.empty();
        }

    }
}
