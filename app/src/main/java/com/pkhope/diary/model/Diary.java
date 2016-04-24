package com.pkhope.diary.model;

import java.io.Serializable;

public class Diary implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String mDate;
	private String mWeek;
	private String mContent;
	
	public String toString() {
		return "Time:" + mDate + "Week:" + mWeek + "Content:" + mContent;
	}
	
	public Diary(String date,String week ,String content) {
		this.mDate = date;
		this.mWeek = week;
		this.mContent = content;
	}
	
	public String getDate() {
		return mDate;
	}

	public String getWeek(){
		return mWeek;
	}
	
	public String getContent() {
		return mContent;
	}

}
