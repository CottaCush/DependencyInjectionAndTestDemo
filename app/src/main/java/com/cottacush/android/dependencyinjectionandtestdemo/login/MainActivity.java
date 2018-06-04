package com.cottacush.android.dependencyinjectionandtestdemo.login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.cottacush.android.dependencyinjectionandtestdemo.R;
import com.cottacush.android.dependencyinjectionandtestdemo.app.App;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements LoginActivityMVP.View {

    @Inject
    LoginActivityMVP.Presenter presenter;

    @BindView(R.id.fname)
    EditText fnameEditText;
    @BindView(R.id.lname)
    EditText lnameEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        ((App)getApplication()).getComponent().inject(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.setView(this);
    }

    @Override
    public String getFirstName() {
        return fnameEditText.getText().toString().trim();
    }

    @Override
    public String getLastName() {
        return lnameEditText.getText().toString().trim();
    }

    @Override
    public void showUserNotAvailable() {
        Toast.makeText(this,"User not available", Toast.LENGTH_LONG).show();
    }

    @Override
    public void showInputError() {
        Toast.makeText(this,"firstname or lastname cannot be empty", Toast.LENGTH_LONG).show();
    }

    @Override
    public void showUserSavedMessage() {
        Toast.makeText(this,"User saved!", Toast.LENGTH_LONG).show();
    }

    @Override
    public void setFirstName(String fname) {
        fnameEditText.setText(fname);
    }

    @Override
    public void setLastName(String lname) {
        lnameEditText.setText(lname);
    }

    @OnClick(R.id.save_button)
    public void saveInput() {}
}