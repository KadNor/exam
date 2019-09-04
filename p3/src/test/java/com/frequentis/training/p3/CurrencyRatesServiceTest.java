package com.frequentis.training.p3;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CurrencyRatesServiceTest {

    private static final double DELTA = 0.000001;

    private CurrencyRatesService sut;
    private RestTemplate restTemplate;

    @Before
    public void setUp() {
        restTemplate = mock(RestTemplate.class);
        sut = new CurrencyRatesService(restTemplate);
    }

    @Test
    public void getExchangeRate_validString_shallReturnTheExchangeRate() {
        // Given
        double expected = 4.5;
        String response = createApiResponseWithValue(expected);

        ResponseEntity responseEntity = mock(ResponseEntity.class);
        when(responseEntity.getBody()).thenReturn(response);
        when(restTemplate.getForEntity(anyString(), any(Class.class))).thenReturn(responseEntity);

        // When
        double actual = sut.getExchangeRate().getValue();

        // Then
        assertEquals(expected, actual, DELTA);
    }

    private String createApiResponseWithValue(final double value) {
        return "{\"rates\":{\"RON\":" + value + "},\"base\":\"EUR\",\"date\":\"2019-09-03\"}";
    }
}
