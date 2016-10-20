package com.andres.money_exchange.mvp.view;

import com.andres.money_exchange.ui.view.BaseView;

import java.util.List;

/**
 * Created by andresdavid on 21/09/16.
 */
public interface MainView extends BaseView {

    void showErrorValidate();
    void showErrorServerResponse();
    void showResult();

}
