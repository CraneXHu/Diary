package com.pkhope.diary;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.security.SecureRandom;

/**
 * Created by thinkpad on 2015/8/11.
 */
public class ResetPasswordActivity extends AppCompatActivity {

    private EditText mEtPwd;
    private EditText mEtConfirm;
    private EditText mEtVerification;
    private String mPwd;
    private String mConfirm;
    private String mVerification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        Button btnReset = (Button) findViewById(R.id.btn_reset);
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkInput();
            }
        });

        Button btnVerfication = (Button) findViewById(R.id.btn_verification);
        btnVerfication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mEtPwd = (EditText) findViewById(R.id.et_pwd);
        mEtConfirm = (EditText) findViewById(R.id.et_confirm);
        mEtVerification = (EditText) findViewById(R.id.et_verification);
    }

    private boolean checkInput(){
        mPwd = mEtPwd.getText().toString();
        mConfirm = mEtConfirm.getText().toString();
        mVerification = mEtVerification.getText().toString();
        if(mPwd.equals("") || mConfirm.equals("")
                || mVerification.equals("")){
            return false;
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
