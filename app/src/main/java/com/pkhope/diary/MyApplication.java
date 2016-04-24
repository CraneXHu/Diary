package com.pkhope.diary;

import android.app.Application;
import android.content.SharedPreferences;

import com.avos.avoscloud.AVOSCloud;
import com.avos.avoscloud.AVObject;
import com.pkhope.diary.model.DiaryLc;
import com.pkhope.diary.model.Document;
import com.pkhope.diary.model.User;

import java.io.IOException;

/**
 * Created by thinkpad on 2015/8/11.
 */
public class MyApplication extends Application{

    private static Document mDocument;
    public static final  int MOD_EDIT = 0;
    public static final  int MOD_VIEW = 1;
    private static int mCurMod = MOD_EDIT;

    @Override
    public void onCreate() {
        super.onCreate();

        AVObject.registerSubclass(User.class);
        AVObject.registerSubclass(DiaryLc.class);
        AVOSCloud.initialize(this, "d8sfcRI2r8wsnakEnSyljGm2-gzGzoHsz", "SmTS3sgmLq9hwlaKEUOWPxCg");

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
