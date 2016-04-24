package com.pkhope.diary.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.WindowManager;

import com.pkhope.diary.MyApplication;
import com.pkhope.diary.R;
import com.pkhope.diary.model.Document;
import com.pkhope.diary.model.User;

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        new SplashTask().execute();

    }

    class SplashTask extends AsyncTask<Void,Void,Void> {

        @Override
        protected Void doInBackground(Void... params) {
            try{

                Thread.sleep(5000);

            }catch (InterruptedException e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            SharedPreferences preference = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            if (preference.getBoolean("network",false) && !User.isLogin()){
                Intent intent = new Intent(SplashActivity.this,LoginActivity.class);
                startActivity(intent);
            } else {

                Document doc = ((MyApplication)getApplication()).getDoc();
                try{
                    doc.readFile();
                }catch (Exception e){
                    e.printStackTrace();
                }

                Intent intent = new Intent(SplashActivity.this,MainActivity.class);
                startActivity(intent);
            }
            finish();
        }
    }
}
