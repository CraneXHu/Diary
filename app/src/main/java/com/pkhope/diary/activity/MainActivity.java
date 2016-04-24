package com.pkhope.diary.activity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.io.IOException;

import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.widget.TextView;

import com.avos.avoscloud.AVAnalytics;
import com.bonet.views.BtCalendarView;
import com.bonet.views.OnDateSelectedListener;
import com.pkhope.diary.model.Diary;
import com.pkhope.diary.adapter.DiaryAdapter;
import com.pkhope.diary.model.Document;
import com.pkhope.diary.MyApplication;
import com.pkhope.diary.adapter.MyPagerAdapter;
import com.pkhope.diary.R;
import com.pkhope.diary.adapter.RecyclerViewAdapter;


public class MainActivity extends AppCompatActivity {
	
	private Document mDocument;
	private DrawerLayout mDrawerLayout;
//	private LinearLayout mSetting;
	private ViewPager mViewPager;
	private ArrayList<View> mViewList;
	private View mLlyList;
	private View mLlyRecyclerView;
	private View mLlyCalender;
	private DiaryAdapter mDiaryAdapter;
	private RecyclerViewAdapter mRVAdapter;
	private Controller mController;
	private ActionBarDrawerToggle mDrawerToggle;
	static private boolean mIsOpened = false;

	private String week [] = {"星期日","星期一","星期二","星期三","星期四","星期五","星期六"};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);

		AVAnalytics.trackAppOpened(getIntent());

		mController = new Controller(this);

		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
//		mSetting = (LinearLayout) findViewById(R.id.lly_nav);
		mDrawerToggle = new ActionBarDrawerToggle(this,mDrawerLayout,0,0){
			public void onDrawerClosed(View view) {
				invalidateOptionsMenu(); // creates call to
			}

			public void onDrawerOpened(View drawerView) {
				invalidateOptionsMenu(); // creates call to
			}
		};
		mDrawerLayout.setDrawerListener(mDrawerToggle);

		mDocument = MyApplication.getDoc();

		initViewPager();
//		initListView();
		initRecyclerView();
		initCalenderView();
	}

	protected void initRecyclerView(){
        RecyclerView recyclerView = (RecyclerView)mLlyRecyclerView.findViewById(R.id.recyclerView);
		recyclerView.setHasFixedSize(true);
		recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
		mRVAdapter = new RecyclerViewAdapter(MyApplication.getDoc().getDiaryManager().getList());
		mRVAdapter.setOnItemClickListener(new RecyclerViewAdapter.OnRecyclerViewItemClickListener() {
			@Override
			public void onItemClick(View view,Diary diary) {
				MyApplication.setCurMod(MyApplication.MOD_VIEW);
				Intent intent = new Intent(MainActivity.this,EditActivity.class);
				intent.putExtra("diary_content",diary.getContent());
				startActivity(intent);
			}
		});
		recyclerView.setAdapter(mRVAdapter);

	}

//	protected void initListView(){
//		ListView listView = (ListView) mLlyList.findViewById(R.id.list_diary);
//		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//			@Override
//			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//				MyApplication.setCurMod(MyApplication.MOD_VIEW);
//				Diary diary = (Diary)parent.getItemAtPosition(position);
//				Intent intent = new Intent(MainActivity.this,EditActivity.class);
//				intent.putExtra("diary_content",diary.getContent());
//				startActivity(intent);
//			}
//		});
//
//		mDiaryAdapter = new DiaryAdapter(this, R.layout.item_diary,MyApplication.getDoc().getDiaryManager().getList());
//		listView.setAdapter(mDiaryAdapter);
//	}

	protected void initCalenderView(){

		final CardView cardView = (CardView)mLlyCalender.findViewById(R.id.lly_item);
		final TextView tvDate = (TextView) mLlyCalender.findViewById(R.id.tv_date);
		final TextView tvWeek = (TextView) mLlyCalender.findViewById(R.id.tv_week);
		final TextView tvContent = (TextView) mLlyCalender.findViewById(R.id.tv_content);

		cardView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				MyApplication.setCurMod(MyApplication.MOD_VIEW);
				Intent intent = new Intent(MainActivity.this, EditActivity.class);
				intent.putExtra("diary_content", tvContent.getText());
				startActivity(intent);
			}
		});

		int diaryCnt = MyApplication.getDoc().getDiaryManager().getDiaryCnt();
		if(diaryCnt == 0){
			cardView.setVisibility(View.INVISIBLE);
		}
		else {
			Diary diary = MyApplication.getDoc().getDiaryManager().getDiary(diaryCnt - 1);
			cardView.setVisibility(View.VISIBLE);
			tvDate.setText(diary.getDate());
			tvWeek.setText(diary.getWeek());
			tvContent.setText(diary.getContent());
		}

		BtCalendarView bc = (BtCalendarView) mLlyCalender.findViewById(R.id.calendar);
		bc.initializeAsGrid();
		bc.setOnDateSelectedListener(new OnDateSelectedListener() {
			@Override
			public void onDateSelected(int year, int month, int day) {
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				String date = formatter.format(new Date(year - 1900, month, day));
				Diary diary = MyApplication.getDoc().getDiaryManager().getDiary(date);
				if (diary != null) {
					cardView.setVisibility(View.VISIBLE);
					tvDate.setText(diary.getDate());
					tvWeek.setText(diary.getWeek());
					tvContent.setText(diary.getContent());

				} else {

					cardView.setVisibility(View.INVISIBLE);
				}
			}
		});
	}

	protected void initViewPager(){

		mViewPager = (ViewPager) findViewById(R.id.vp_main);
		LayoutInflater inflater = getLayoutInflater();
//		mLlyList = inflater.inflate(R.layout.list, null);
		mLlyRecyclerView = inflater.inflate(R.layout.recyclerview,null);
		mLlyCalender = inflater.inflate(R.layout.calendar,null);

		mViewList = new ArrayList<View>();
//		mViewList.add(mLlyList);
		mViewList.add(mLlyRecyclerView);
		mViewList.add(mLlyCalender);
		mViewPager.setAdapter(new MyPagerAdapter( mViewList));
		mViewPager.setCurrentItem(0);
	}
	
	@Override
	public void onStop() {
		super.onStop();
		try {
			mDocument.writeFile("diary.dat");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		switch(requestCode) {
		case MyApplication.MOD_EDIT:
			if(resultCode == RESULT_OK) {
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				String date = formatter.format(new Date());
				int iWeek = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
				String returnedData = data.getStringExtra("data_return");
				Diary diary = mDocument.getDiaryManager().createDiary(date, week[iWeek-1], returnedData);
				mDocument.getDiaryManager().addDairy(diary);
//				mDiaryAdapter.notifyDataSetChanged();
				mRVAdapter.setList(MyApplication.getDoc().getDiaryManager().getList());
				mRVAdapter.notifyDataSetChanged();
			}
			break;
		default:
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_main, menu);

		SearchManager searchManager =
				(SearchManager) getSystemService(Context.SEARCH_SERVICE);
		MenuItem searchItem = menu.findItem(R.id.action_search);
		SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
		ComponentName componentName = getComponentName();

		searchView.setSearchableInfo(
				searchManager.getSearchableInfo(componentName));
		searchView.setQueryHint(getString(R.string.search_hint));
		searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
			@Override
			public boolean onQueryTextSubmit(String s) {
				return true;
			}

			@Override
			public boolean onQueryTextChange(String s) {
				mRVAdapter.setList(MyApplication.getDoc().getDiaryManager().getList());
				mRVAdapter.getFilter().filter(s);
				return true;
			}
		});
		return true;
	}

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		return super.onPrepareOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (mDrawerToggle.onOptionsItemSelected(item)){
			return true;
		}
		switch(item.getItemId()) {
			case R.id.action_add:
				MyApplication.setCurMod(MyApplication.MOD_EDIT);
                Intent intent = new Intent(MainActivity.this,EditActivity.class);
				startActivityForResult(intent,MyApplication.MOD_EDIT);
				break;
			case R.id.action_search:
				break;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		mDrawerToggle.onConfigurationChanged(newConfig);
	}
}
