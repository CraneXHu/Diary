package com.pkhope.diary.callable;

import com.avos.avoscloud.AVException;
import com.pkhope.diary.model.User;

/**
 * Created by thinkpad on 2016/4/24.
 */
public interface OnLogInCallback {
    void done(User user, AVException e);
}