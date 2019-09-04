package com.frequentis.training.p3;

import org.junit.Before;
import org.junit.Test;

import com.frequentis.training.p3.models.ExchangeRate;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CurrencyRatesAlertingEngineTest {

    private static final int TIMEOUT = 10000;
    private CurrencyRatesAlertingEngine sut;
    private CurrencyRatesService service;
    private CurrencyChangeListener listener;

    @Before
    public void setUp() {
        service = mock(CurrencyRatesService.class);
    }

    @Test
    public void shallCallListenersUponAChange() {
        // Given
        when(service.getExchangeRate()).thenReturn(createExchangeRateWithValue(4.5), createExchangeRateWithValue(5));
        sut = new CurrencyRatesAlertingEngine(service, 0);
        listener = mock(CurrencyChangeListener.class);
        sut.addListener(listener);

        // When
        sut.start();

        // Then
        verify(listener, timeout(TIMEOUT).times(1)).currencyChanged(any());
        sut.stopEngine();
    }

    @Test
    public void shallNotCallListenersWithoutAChange() {
        // Given
        when(service.getExchangeRate()).thenReturn(createExchangeRateWithValue(4.5));
        sut = new CurrencyRatesAlertingEngine(service, 0);
        listener = mock(CurrencyChangeListener.class);
        sut.addListener(listener);

        // When
        sut.start();

        // Then
        verify(listener, timeout(TIMEOUT).times(0)).currencyChanged(any());
        sut.stopEngine();
    }

    private ExchangeRate createExchangeRateWithValue(final double value) {
        return new ExchangeRate("EUR", "RON", value);
    }
}
