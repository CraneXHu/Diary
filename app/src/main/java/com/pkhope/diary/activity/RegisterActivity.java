package com.pkhope.diary.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;
import com.pkhope.diary.MyApplication;
import com.pkhope.diary.R;
import com.pkhope.diary.model.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

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
                    Toast.makeText(getApplicationContext(),"输入不合法",Toast.LENGTH_SHORT).show();
                    return;
                }


                AVQuery<User> query = AVObject.getQuery(User.class);
                query.whereEqualTo("userName",mUser);
                query.findInBackground(new FindCallback<User>() {
                    @Override
                    public void done(List<User> list, AVException e) {
                        if(list.size() == 0){
                            User user = new User();
                            user.setUserId(mUser);
                            user.setPassword(mPwd);
                            user.saveInBackground();

                            Intent intent = new Intent(RegisterActivity.this,MainActivity.class);
                            startActivity(intent);
                            finish();

                        } else {
                            Toast.makeText(getApplicationContext(),"用户名已存在",Toast.LENGTH_SHORT).show();
                        }
                    }
                });

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
