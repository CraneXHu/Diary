package com.pkhope.diary.model;

import com.avos.avoscloud.AVClassName;
import com.avos.avoscloud.AVObject;

/**
 * Created by thinkpad on 2016/4/24.
 */
@AVClassName("DiaryLc")
public class DiaryLc extends AVObject{

    public static final Creator CREATOR = AVObjectCreator.instance;

    public String getUserName(){
        return getString("userName");
    }

    public void setUserName(String userName){
        put("userName",userName);
    }

    public String getDate(){
        return  getString("Date");
    }

    public void setDate(String date){
        put("Date",date);
    }

    public String getWeek(){
        return  getString("Week");
    }

    public void setWeek(String week){
        put("Week",week);
    }

    public String getContent(){
        return  getString("Content");
    }

    public void setContent(String content){
        put("Content",content);
    }
}
