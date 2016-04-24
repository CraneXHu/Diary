package com.pkhope.diary.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.avos.avoscloud.AVObject;
import com.pkhope.diary.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by thinkpad on 2015/8/11.
 */
public class FeedBackActivity extends AppCompatActivity {


    @InjectView(R.id.et_feedback)
    EditText mFeedBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        ButterKnife.inject(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_feedback, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.action_feedback:
                AVObject feedBack = new AVObject("feedback");
                feedBack.put("content",mFeedBack.getText());
                feedBack.saveInBackground();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
