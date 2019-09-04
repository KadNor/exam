package com.frequentis.training.p3;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.frequentis.training.p3.models.ExchangeRate;

public class CurrencyRatesService {

    private static final String PREFIX_DELIMITER = "RON\":";
    private static final String SUFFIX_DELIMITER = "}";
    private static final String API_URL = "https://api.exchangeratesapi.io/latest?symbols=RON";
    private RestTemplate restTemplate;

    public CurrencyRatesService(final RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ExchangeRate getExchangeRate() {
        ResponseEntity<String> response = restTemplate.getForEntity(API_URL, String.class);

        return new ExchangeRate("EUR", "RON", parseExchangeRate(response.getBody()));
    }

    private double parseExchangeRate(final String apiResponse) {
        return Double.parseDouble(apiResponse.split(PREFIX_DELIMITER)[1].split(SUFFIX_DELIMITER)[0]);
    }
}
