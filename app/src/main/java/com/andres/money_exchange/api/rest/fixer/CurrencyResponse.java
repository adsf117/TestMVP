package com.andres.money_exchange.api.rest.fixer;

import java.util.Map;

/**
 * Created by andresdavid on 21/09/16.
 */
public class CurrencyResponse {
    private  String base;
    private  String date;

    private Map<String, Double> rates;

    public CurrencyResponse() {
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    public Map<String, Double> getRates() {
        return rates;
    }

    public void setRates(Map<String, Double> rates) {
        this.rates = rates;
    }
}
