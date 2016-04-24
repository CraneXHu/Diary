package com.pkhope.diary.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.MenuItem;
import android.widget.TextView;

import com.pkhope.diary.R;

/**
 * Created by thinkpad on 2015/8/11.
 */
public class HelpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        String html = "<textarea>1.单机与联网<br>" +
                "&nbsp;&nbsp;&nbsp;&nbsp;单机模式不会使用网络功能，即不会将数据传到服务器，同时不能在不同设备之间进行同步。</textarea>";

        TextView tvHelp = (TextView)findViewById(R.id.tv_help);
        tvHelp.setText(Html.fromHtml(html));
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
