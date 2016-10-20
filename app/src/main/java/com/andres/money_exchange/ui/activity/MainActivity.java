package com.andres.money_exchange.ui.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.andres.money_exchange.R;
import com.andres.money_exchange.mvp.presenter.CurrencyPresenter;
import com.andres.money_exchange.mvp.presenter.CurrencyPresenterImpl;
import com.andres.money_exchange.mvp.view.MainView;
import com.andres.money_exchange.ui.activity.BaseActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements MainView {

    @BindView(R.id.amount_to_convert)
    EditText mAmount_to_convert;
    @BindView(R.id.list_result_convert)
    ListView list_result_convert;

    CurrencyPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        presenter = new CurrencyPresenterImpl(this);
        presenter.getDataFromServer();
        mAmount_to_convert.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                presenter.validateConvert(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    @Override
    public void showErrorValidate() {
        mAmount_to_convert.setError(getString(R.string.error_field_required));
    }

    @Override
    public void showErrorServerResponse() {
        Toast.makeText(this, getString(R.string.network_error), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showResult() {
        List<String> rtaConverts = presenter.convertValues(mAmount_to_convert.getText().toString());
        if (rtaConverts.size() <= 0) {

            Toast.makeText(this, "No result", Toast.LENGTH_SHORT).show();
        }

        list_result_convert.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, rtaConverts));
    }

}
