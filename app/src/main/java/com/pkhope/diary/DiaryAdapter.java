package com.pkhope.diary;

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class DiaryAdapter extends ArrayAdapter<Diary> {
    
	private int mResourceId;
	
	public DiaryAdapter(Context context, int textViewResourceId, List<Diary> objects) {
		super(context, textViewResourceId,objects);
		mResourceId = textViewResourceId;
	}
	
	@Override
	public View getView(int position, View converView, ViewGroup parent) {
		Diary diary = getItem(position);
		View view = LayoutInflater.from(getContext()).inflate(mResourceId, null);
		TextView date = (TextView) view.findViewById(R.id.tv_date);
		TextView week = (TextView) view.findViewById(R.id.tv_week);
		TextView diarycontent = (TextView) view.findViewById(R.id.tv_content);
		date.setText(diary.getDate());
		week.setText(diary.getWeek());
		diarycontent.setText(diary.getContent());
		view.setBackgroundColor(Color.WHITE);
		return view;
	}
}
