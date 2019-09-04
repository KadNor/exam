package com.frequentis.training.p3;

import java.util.ArrayList;
import java.util.List;

import com.frequentis.training.p3.models.ExchangeRate;

public class CurrencyRatesAlertingEngine extends Thread {

    private final CurrencyRatesService currencyRatesService;
    private List<CurrencyChangeListener> listeners;
    private int checkPeriodTime;
    private ExchangeRate cachedExchangeRate;
    private boolean stop;

    public CurrencyRatesAlertingEngine(final CurrencyRatesService currencyRatesService, final int checkPeriodTimeInMilliseconds) {
        this.currencyRatesService = currencyRatesService;
        this.listeners = new ArrayList<>();
        this.checkPeriodTime = checkPeriodTimeInMilliseconds;
        this.cachedExchangeRate = currencyRatesService.getExchangeRate();
        this.stop = false;
    }

    public void addListener(final CurrencyChangeListener listener) {
        listeners.add(listener);
    }

    @Override
    public void run() {
        while (!stop) {
            try {
                Thread.sleep(checkPeriodTime);
            } catch (InterruptedException e) {
                // ignore
            }

            ExchangeRate newExchangeRate = currencyRatesService.getExchangeRate();
            if (!newExchangeRate.equals(cachedExchangeRate)) {
                listeners.forEach(listener -> listener.currencyChanged(newExchangeRate));
                cachedExchangeRate = newExchangeRate;
            }
        }
    }

    public void stopEngine() {
        stop = true;
    }
}
