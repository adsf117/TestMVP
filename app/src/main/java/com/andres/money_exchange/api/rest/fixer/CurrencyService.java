package com.andres.money_exchange.api.rest.fixer;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by andresdavid on 21/09/16.
 */
public interface CurrencyService {

    @GET("/latest")
    Call<CurrencyResponse> getCurrencyDolars(@Query("base") String base);
}
