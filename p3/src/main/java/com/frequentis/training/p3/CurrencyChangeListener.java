package com.frequentis.training.p3;

import com.frequentis.training.p3.models.ExchangeRate;

@FunctionalInterface
public interface CurrencyChangeListener {

    void currencyChanged(ExchangeRate newExchangeRate);
}
