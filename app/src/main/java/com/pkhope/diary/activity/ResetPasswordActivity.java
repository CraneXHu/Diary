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
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.FindCallback;
import com.avos.avoscloud.RequestPasswordResetCallback;
import com.pkhope.diary.R;
import com.pkhope.diary.model.Diary;
import com.pkhope.diary.model.DiaryLc;
import com.pkhope.diary.model.User;

import java.security.SecureRandom;
import java.util.List;

/**
 * Created by thinkpad on 2015/8/11.
 */
public class ResetPasswordActivity extends AppCompatActivity {

    private EditText mUser;

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

                AVQuery<User> query = AVObject.getQuery(User.class);
                query.whereEqualTo("userId",mUser.getText());
                query.findInBackground(new FindCallback<User>() {
                    @Override
                    public void done(List<User> list, AVException e) {
                        if (e == null){
                            AVUser.requestPasswordResetInBackground(mUser.getText().toString(), new RequestPasswordResetCallback() {
                                public void done(AVException e) {
                                    if (e == null) {
                                        // 已发送一份重置密码的指令到用户的邮箱
                                        Intent intent = new Intent(ResetPasswordActivity.this,ResetPasswordActivity.class);
                                        startActivity(intent);
                                        finish();
                                    } else {
                                        // 重置密码出错。
                                        Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }else{
                            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });



        mUser = (EditText) findViewById(R.id.et_user);
    }

    private boolean checkInput(){
        if (mUser.getText().equals("")){
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
