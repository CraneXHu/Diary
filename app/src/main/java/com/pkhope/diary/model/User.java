package com.pkhope.diary.model;

import com.avos.avoscloud.AVClassName;
import com.avos.avoscloud.AVObject;

/**
 * Created by pkhope on 2016/4/20.
 */
@AVClassName("_User")
public class User extends AVObject {

    public static final Creator CREATOR = AVObjectCreator.instance;

    public String getUserId(){
        return getString("userId");
    }

    public void setUserId(String userId){
        put("userId",userId);
    }

    public String getPassword(){
        return getString("password");
    }

    public void setPassword(String password){
        put("password",password);
    }
}
