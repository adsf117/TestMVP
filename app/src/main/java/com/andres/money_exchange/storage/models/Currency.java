package com.andres.money_exchange.storage.models;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by andresdavid on 21/09/16.
 */
public class Currency extends RealmObject {

    @PrimaryKey
    private long idcurrency;
    private  String base;
    private  String date;
    private RealmList<Rate> rate;

    public String getBase() {
        return base;
    }

    public long getIdcurrency() {
        return idcurrency;
    }

    public void setIdcurrency(long idcurrency) {
        this.idcurrency = idcurrency;
    }
    public void setBase(String base) {
        this.base = base;
    }

    public RealmList<Rate> getRate() {
        return rate;
    }

    public void setRate(RealmList<Rate> rate) {
        this.rate = rate;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


}
