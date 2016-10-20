package com.andres.money_exchange.currency.repository;

import android.content.Context;

import com.andres.money_exchange.R;
import com.andres.money_exchange.api.DetailsApi;
import com.andres.money_exchange.api.rest.ServiceCreator;
import com.andres.money_exchange.api.rest.fixer.CurrencyResponse;
import com.andres.money_exchange.api.rest.fixer.CurrencyService;
import com.andres.money_exchange.mvp.presenter.CurrencyPresenter;
import com.andres.money_exchange.storage.database.RealmManager;
import com.andres.money_exchange.storage.models.Currency;
import com.andres.money_exchange.storage.models.Rate;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.realm.Realm;
import io.realm.RealmList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by andresdavid on 21/09/16.
 */
public class CurrencyRepositoryImpl implements CurrencyRepository {


    private Context mContext;

    private static CurrencyRepositoryImpl mCurrencyRepository;

    public static CurrencyRepositoryImpl getInstance(Context context) {
        if (mCurrencyRepository == null) {
            mCurrencyRepository = new CurrencyRepositoryImpl(context.getApplicationContext());
        }
        return mCurrencyRepository;
    }

    public CurrencyRepositoryImpl(Context context) {
        this.mContext = context;
    }

    @Override
    public void getCurrencyFromServer(final CurrencyPresenter presenter) {
        CurrencyService userService = ServiceCreator.createService(CurrencyService.class);
        Call<CurrencyResponse> call = userService.getCurrencyDolars(DetailsApi.BASE_CURRENCY);
        call.enqueue(new Callback<CurrencyResponse>() {
            @Override
            public void onResponse(Call<CurrencyResponse> call, Response<CurrencyResponse> response) {
                if (response.isSuccessful()) {
                    Currency currency = new Currency();
                    currency.setBase(response.body().getBase());
                    currency.setDate((response.body().getDate()));
                    RealmList<Rate> rateRealmList = new RealmList<>();
                    Map<String, Double> map = response.body().getRates();
                    for (Map.Entry<String, Double> entry : map.entrySet()) {
                        Rate rate = new Rate();
                        rate.setKey(entry.getKey());
                        rate.setValue(entry.getValue());
                        rateRealmList.add(rate);
                    }
                    currency.setRate(rateRealmList);
                    saveData(currency);
                } else {
                    presenter.showErrorServerResponse();
                }
            }

            @Override
            public void onFailure(Call<CurrencyResponse> call, Throwable t) {
                presenter.showErrorServerResponse();
            }
        });

    }
    @Override
    public  void saveData(final Currency currencyData) {
        RealmManager.getInstance(mContext).getRealm().executeTransactionAsync(
                new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        realm.insertOrUpdate(currencyData);
                    }
                }
        );
    }


    @Override
    public List<String> getConvertion(String dolar_amout) {

        Currency currency = RealmManager.getInstance(mContext).getRealm().where(Currency.class).findFirst();
        Map<String, String> rtaConverts = new HashMap<String, String>();
        DecimalFormat df = new DecimalFormat("#.000");
        for (Rate rate : currency.getRate()) {
            rtaConverts.put(rate.getKey(), df.format(Double.valueOf(dolar_amout) * rate.getValue()));
        }
        List<String> items = new ArrayList<>();
        items.add(String.format(mContext.getString(R.string.currency_uk), rtaConverts.get(DetailsApi.CURRENCY_GBP).toString()));
        items.add(String.format(mContext.getString(R.string.currency_brazil), rtaConverts.get(DetailsApi.CURRENCY_BRL).toString()));
        items.add(String.format(mContext.getString(R.string.currency_eu), rtaConverts.get(DetailsApi.CURRENCY_EUR).toString()));
        items.add(String.format(mContext.getString(R.string.currency_japan), rtaConverts.get(DetailsApi.CURRENCY_JPY).toString()));
        return items;
    }
}
