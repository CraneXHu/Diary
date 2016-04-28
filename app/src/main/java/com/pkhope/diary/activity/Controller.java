package com.pkhope.diary.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import com.avos.avoscloud.AVUser;
import com.pkhope.diary.R;
import com.pkhope.diary.activity.AboutActivity;
import com.pkhope.diary.activity.FeedBackActivity;
import com.pkhope.diary.activity.HelpActivity;
import com.pkhope.diary.activity.SettingActivity;
import com.pkhope.diary.model.User;

import de.psdev.licensesdialog.LicensesDialog;
import de.psdev.licensesdialog.licenses.ApacheSoftwareLicense20;
import de.psdev.licensesdialog.licenses.License;
import de.psdev.licensesdialog.model.Notice;
import de.psdev.licensesdialog.model.Notices;

/**
 * Created by thinkpad on 2015/8/10.
 */
public class Controller {

    private Activity mActivity;
    private LinearLayout mLlySetting;
    private LinearLayout mLlyHelp;
    private LinearLayout mLlyFeedBack;
    private LinearLayout mLlyLicense;
    private LinearLayout mLlyAbout;
    private LinearLayout mLlyLogout;

    Controller(Context context){
        mActivity = (Activity)context;
        init();
        isLogin();
    }

    private void init(){
        mLlySetting = (LinearLayout)mActivity.findViewById(R.id.lly_setting);
        mLlySetting.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    mLlySetting.setBackgroundResource(R.drawable.nav_item_pressed);
                }
                else if(event.getAction() == MotionEvent.ACTION_UP){
                    mLlySetting.setBackgroundResource(R.drawable.nav_item_normal);
                    Intent intent = new Intent(mActivity,SettingActivity.class);
                    mActivity.startActivity(intent);
                }
                return true;
            }
        });

        mLlyHelp = (LinearLayout)mActivity.findViewById(R.id.lly_help);
        mLlyHelp.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    mLlyHelp.setBackgroundResource(R.drawable.nav_item_pressed);
                } else if(event.getAction() == MotionEvent.ACTION_UP){
                    mLlyHelp.setBackgroundResource(R.drawable.nav_item_normal);
                    Intent intent = new Intent(mActivity, HelpActivity.class);
                    mActivity.startActivity(intent);
                }
                return true;
            }
        });

        mLlyFeedBack = (LinearLayout)mActivity.findViewById(R.id.lly_feedback);
        mLlyFeedBack.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    mLlyFeedBack.setBackgroundResource(R.drawable.nav_item_pressed);
                } else if(event.getAction() == MotionEvent.ACTION_UP){
                    mLlyFeedBack.setBackgroundResource(R.drawable.nav_item_normal);
                    Intent intent = new Intent(mActivity, FeedBackActivity.class);
                    mActivity.startActivity(intent);
                }
                return true;
            }
        });

        mLlyLicense = (LinearLayout) mActivity.findViewById(R.id.lly_license);
        mLlyLicense.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    mLlyLicense.setBackgroundResource(R.drawable.nav_item_pressed);
                } else if(event.getAction() == MotionEvent.ACTION_UP){
                    mLlyLicense.setBackgroundResource(R.drawable.nav_item_normal);
                    Notices notices = new Notices();
                    String name = "LicensesDialog";
                    String url = "http://psdev.de";
                    String copyright = "Copyright 2013 Philip Schiffer <admin@psdev.de>";
                    License license = new ApacheSoftwareLicense20();
                    Notice notice = new Notice(name, url, copyright, license);
                    notices.addNotice(notice);
                    new LicensesDialog.Builder(mActivity)
                            .setNotices(notices)
                            .build()
                            .show();
                }
                return true;
            }
        });

        mLlyAbout = (LinearLayout)mActivity.findViewById(R.id.lly_about);
        mLlyAbout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    mLlyAbout.setBackgroundResource(R.drawable.nav_item_pressed);
                } else if(event.getAction() == MotionEvent.ACTION_UP){
                    mLlyAbout.setBackgroundResource(R.drawable.nav_item_normal);
                    Intent intent = new Intent(mActivity, AboutActivity.class);
                    mActivity.startActivity(intent);
                }
                return true;
            }
        });

        mLlyLogout = (LinearLayout)mActivity.findViewById(R.id.lly_logout);
        mLlyLogout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    mLlyLogout.setBackgroundResource(R.drawable.nav_item_pressed);
                } else if(event.getAction() == MotionEvent.ACTION_UP){
                    mLlyLogout.setBackgroundResource(R.drawable.nav_item_normal);
                    AVUser.logOut();
                    Intent intent = new Intent(mActivity,LoginActivity.class);
                    mActivity.startActivity(intent);
                }
                return true;
            }
        });

    }

    public void isLogin(){

        if(User.isLogin()){
            mLlyLogout.setVisibility(View.VISIBLE);
        }else{
            mLlyLogout.setVisibility(View.GONE);
        }
    }

}
