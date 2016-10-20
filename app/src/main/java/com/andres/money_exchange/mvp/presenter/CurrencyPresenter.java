package com.andres.money_exchange.mvp.presenter;

import java.util.List;

/**
 * Created by andresdavid on 21/09/16.
 */
public interface CurrencyPresenter {
    void validateConvert(String amount);
    void getDataFromServer();
    void showErrorServerResponse();
    List<String> convertValues(String dolars_amount);


}
