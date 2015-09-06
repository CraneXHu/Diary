package com.pkhope.diary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by thinkpad on 2015/8/11.
 */
public class LoginActivity extends AppCompatActivity {

    private Button mBtnLogin;
    private Button mBtnRegister;
  //  private TextView mTvForgetpassword;
    private EditText mEtUser;
    private EditText mEtPwd;
    private String mUser;
    private String mPwd;
    public static final String URL = "http://10.0.3.2/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        mBtnLogin = (Button) findViewById(R.id.login);
        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String url = "http://10.0.3.2/?action=login&user=00000000000&" +
//                        "pwd=123456";
//                JSONObject obj = new JSONObject();
//                try {
//                    obj.put("action","login");
//                    obj.put("user","example@gmail.com");
//                    obj.put("pwd","123456");
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
                if (!checkInput()){
                    return;
                }

                String url = "" + URL + "?action=login" + "&user=" + mUser
                        + "&pwd=" + mPwd;

                JsonObjectRequest jor = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String res = (String) response.get("result");
                            if (res != null && res.equals("succeed")){
                                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                                startActivity(intent);
                                finish();
                            } else {
                                Toast.makeText(LoginActivity.this,"user or password error!",Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(LoginActivity.this,"Network error!",Toast.LENGTH_SHORT).show();
                    }
                });

                jor.setTag("login");
                MyApplication.getRequestQueue().add(jor);
            }
        });

        mBtnRegister = (Button) findViewById(R.id.register);
        mBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });

        TextView tvFp = (TextView) findViewById(R.id.tv_forget_password);
        tvFp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,ResetPasswordActivity.class);
                startActivity(intent);
            }
        });

        mEtUser = (EditText) findViewById(R.id.et_user);
        mEtPwd = (EditText) findViewById(R.id.et_pwd);
    }

    private boolean checkInput() {
        mUser = mEtUser.getText().toString();
        mPwd = mEtPwd.getText().toString();
        if (mUser.equals("") || mPwd.equals("")){
            return false;
        }
        return true;
    }

    @Override
    protected void onStop() {
        super.onStop();
        MyApplication.getRequestQueue().cancelAll("login");
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
