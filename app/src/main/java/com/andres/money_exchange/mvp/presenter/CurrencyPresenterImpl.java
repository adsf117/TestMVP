package com.andres.money_exchange.mvp.presenter;

import com.andres.money_exchange.currency.iterector.CurrencyInteractorImpl;
import com.andres.money_exchange.currency.repository.CurrencyRepositoryImpl;
import com.andres.money_exchange.mvp.view.MainView;
import com.andres.money_exchange.ui.view.OnConvertListener;

import java.util.List;

/**
 * Created by andresdavid on 21/09/16.
 */
public class CurrencyPresenterImpl implements CurrencyPresenter, OnConvertListener {


    private MainView mView;
    private CurrencyInteractorImpl mInteractor;

    public CurrencyPresenterImpl(MainView view) {
        this.mView = view;
        this.mInteractor = new CurrencyInteractorImpl();

    }

    @Override
    public void validateConvert(String amount) {
        if (mView != null) {
            mInteractor.validateConvert(amount, this);
        }
    }

    @Override
    public void getDataFromServer() {
        CurrencyRepositoryImpl.getInstance(mView.getViewContext()).getCurrencyFromServer(this);
    }

    @Override
    public void showErrorServerResponse() {
        if (mView != null) {
            mView.showErrorServerResponse();
        }
    }

    @Override
    public List<String> convertValues(String dolars_amount) {
        return CurrencyRepositoryImpl.getInstance(mView.getViewContext()).getConvertion(dolars_amount);

    }

    @Override
    public void setErrorDolarAmount() {
        if (mView != null) {
            mView.showErrorValidate();
        }

    }

    @Override
    public void showResult() {
        if (mView != null) {
            mView.showResult();
        }
    }
}
