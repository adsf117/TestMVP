package com.andres.money_exchange.currency.iterector;

import com.andres.money_exchange.ui.view.OnConvertListener;

/**
 * Created by andresdavid on 21/09/16.
 */
public interface CurrencyInteractor {
    void validateConvert(String amount, OnConvertListener listener);

}
