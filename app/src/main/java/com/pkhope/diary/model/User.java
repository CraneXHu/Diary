package com.pkhope.diary.model;

import com.avos.avoscloud.AVClassName;
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.LogInCallback;
import com.avos.avoscloud.SignUpCallback;
import com.pkhope.diary.callable.OnLogInCallback;
import com.pkhope.diary.callable.OnSignUpCallback;

/**
 * Created by pkhope on 2016/4/20.
 */
@AVClassName("User")
public class User extends AVUser {

    public static final Creator CREATOR = AVObjectCreator.instance;

//    public String getUserId(){
//        return getString("userId");
//    }
//
//    public void setUserId(String userId){
//        put("userId",userId);
//    }
//
//    public String getPassword(){
//        return getString("password");
//    }
//
//    public void setPassword(String password){
//        put("password",password);
//    }

    public static boolean isLogin(){
        return AVUser.getCurrentUser() != null;
    }

    public static void logInInBackground(String username, String password, final OnLogInCallback callback) {
        logInInBackground(username, password, new LogInCallback<User>() {
            @Override public void done(User user, AVException e) {
                callback.done(user, e);
            }
        }, User.class);
    }

    public void signUpInBackground(final OnSignUpCallback callback) {
        signUpInBackground(new SignUpCallback() {
            @Override public void done(AVException e) {
                callback.done(e);
            }
        });
    }
}
