package com.pkhope.diary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by thinkpad on 2015/8/11.
 */
public class RegisterActivity extends AppCompatActivity {

    private Button mBtnRegister;
    private EditText mEtUser;
    private EditText mEtPwd;
    private EditText mEtConfirm;
    private EditText mEtVerification;
    private String mUser;
    private String mPwd;
    private String mConfirm;
    private String mVerification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        mBtnRegister = (Button) findViewById(R.id.btn_register);
        mBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!checkInput()){
                    return;
                }

                String url = "" + LoginActivity.URL + "?action=register" + "&user=" + mUser
                        + "&pwd=" + mPwd + "&confirm=" + mConfirm + "&verification=" + mVerification;

                JsonObjectRequest jor = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String res = (String) response.get("result");
                            if (res != null && res.equals("succeed")){
                                Intent intent = new Intent(RegisterActivity.this,MainActivity.class);
                                startActivity(intent);
                                finish();
                            } else {
                                Toast.makeText(getApplicationContext(), "user or password error!", Toast.LENGTH_SHORT);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),"Network error!",Toast.LENGTH_SHORT);
                    }
                });

                jor.setTag("register");
                MyApplication.getRequestQueue().add(jor);
            }
        });

        mEtUser = (EditText) findViewById(R.id.et_user);
        mEtPwd = (EditText) findViewById(R.id.et_pwd);
        mEtConfirm = (EditText) findViewById(R.id.et_confirm);
        mEtVerification = (EditText) findViewById(R.id.et_verification);
    }

    private boolean checkInput(){
        mUser = mEtUser.getText().toString();
        mPwd = mEtPwd.getText().toString();
        mConfirm = mEtConfirm.getText().toString();
        mVerification = mEtVerification.getText().toString();

        if (mUser.equals("") || mPwd.equals("") || mConfirm.equals("")
                || mVerification.equals("")){
            return false;
        }
        return true;
    }

    @Override
    protected void onStop() {
        super.onStop();
        MyApplication.getRequestQueue().cancelAll("register");
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
