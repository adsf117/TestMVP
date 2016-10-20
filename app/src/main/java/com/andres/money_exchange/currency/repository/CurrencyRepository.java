package com.andres.money_exchange.currency.repository;

import com.andres.money_exchange.mvp.presenter.CurrencyPresenter;
import com.andres.money_exchange.storage.models.Currency;


import java.util.List;


/**
 * Created by andresdavid on 21/09/16.
 */
public interface CurrencyRepository {
    void getCurrencyFromServer(CurrencyPresenter presenter);
    void saveData(Currency currencyData);
    List<String> getConvertion(String amount);
}
