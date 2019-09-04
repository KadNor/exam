package com.frequentis.training.p3.models;

import java.io.Serializable;
import java.util.Objects;

public class ExchangeRate implements Serializable {

    private final String from;
    private final String to;
    private double value;

    public ExchangeRate(final String from, final String to, final double value) {
        this.from = from;
        this.to = to;
        this.value = value;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public double getValue() {
        return value;
    }

    public void setValue(final double value) {
        this.value = value;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ExchangeRate exchangeRate = (ExchangeRate) o;
        return Double.compare(exchangeRate.value, value) == 0
                && Objects.equals(from, exchangeRate.from)
                && Objects.equals(to, exchangeRate.to);
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, to, value);
    }

    @Override
    public String toString() {
        return "from: " + from +
                ", to: " + to +
                ", value: " + value;
    }
}
