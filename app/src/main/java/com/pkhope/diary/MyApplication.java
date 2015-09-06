package com.pkhope.diary;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.io.IOException;

/**
 * Created by thinkpad on 2015/8/11.
 */
public class MyApplication extends Application{

    private static Document mDocument;
    private static RequestQueue mRequestQueue;
    public static final  int MOD_EDIT = 0;
    public static final  int MOD_VIEW = 1;
    private static int mCurMod = MOD_EDIT;

    @Override
    public void onCreate() {
        super.onCreate();

        mRequestQueue = Volley.newRequestQueue(getApplicationContext());

        initDoc();
    }

    protected void initDoc(){
        try {
            mDocument = new Document(getApplicationContext());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static RequestQueue getRequestQueue(){
        return mRequestQueue;
    }

    public static Document getDoc(){
        return mDocument;
    }

    public static int getCurMod(){
        return mCurMod;
    }

    public static void setCurMod(int mod){
        mCurMod = mod;
    }
}
