package com.andres.money_exchange.currency.iterector;

import android.text.TextUtils;

import com.andres.money_exchange.ui.view.OnConvertListener;

/**
 * Created by andresdavid on 21/09/16.
 */
public class CurrencyInteractorImpl implements CurrencyInteractor {
    @Override
    public void validateConvert(String amount, OnConvertListener listener) {

        if(TextUtils.isEmpty(amount))
        {
            listener.setErrorDolarAmount();
        }
        else{
            listener.showResult();
        }

    }
}
