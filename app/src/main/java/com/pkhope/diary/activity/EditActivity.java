package com.pkhope.diary.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.pkhope.diary.MyApplication;
import com.pkhope.diary.R;

public class EditActivity extends AppCompatActivity {
	private EditText mEditText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_edit);

		ActionBar actionBar = getSupportActionBar();
		actionBar.setHomeButtonEnabled(true);
		actionBar.setDisplayHomeAsUpEnabled(true);

        initView();
	}

	protected void initView(){
		mEditText = (EditText) findViewById(R.id.edit_text);
		Intent intent = getIntent();
		String data = intent.getStringExtra("diary_content");
		mEditText.setText(data);
		if (MyApplication.getCurMod() == MyApplication.MOD_EDIT){
			mEditText.setEnabled(true);
		}else{
			mEditText.setEnabled(false);
		}
	}
	
	@Override
	public void onBackPressed() {
//		prepareData();
		finish();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_edit, menu);
        MenuItem item = menu.findItem(R.id.action_ok);
        if (MyApplication.getCurMod() == MyApplication.MOD_VIEW){
            item.setVisible(false);
        }
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()) {
			case android.R.id.home:
				finish();
				break;
            case R.id.action_ok:
                prepareData();
                finish();
                break;
		}
		return super.onOptionsItemSelected(item);
	}

	protected void prepareData(){
		String data = mEditText.getText().toString();
		if (data.equals("")){
			Toast.makeText(EditActivity.this,"No content",Toast.LENGTH_SHORT).show();
			return;
		}
		Intent intent = new Intent();
		intent.putExtra("data_return", data);
		setResult(RESULT_OK,intent);
	}
}
