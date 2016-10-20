package com.andres.money_exchange.ui.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.andres.money_exchange.ui.view.BaseView;

/**
 * Created by andresdavid on 21/09/16.
 */
public class BaseActivity extends AppCompatActivity implements BaseView {

    @Override
    public Context getViewContext() {
        return this;
    }
}
