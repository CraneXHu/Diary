package com.pkhope.diary.model;

import java.util.ArrayList;
import java.util.List;

public class DiaryManager {
	
	private List<Diary> mDiaryList;
	
	public DiaryManager() {
		mDiaryList = new ArrayList<Diary>();
	}
	
	public List<Diary> getList() {
		return mDiaryList;
	}
	public Diary createDiary(String date,String week, String content) {
		Diary diary = new Diary(date,week,content);
//		myDiaryList.add(diary);
		return diary;
	}
	
	public void addDairy(Diary diary) {
		mDiaryList.add(diary);
	}
	
	public int getDiaryCnt(){

		return mDiaryList.size();
	}
	
	public Diary getDiary(int index){
		int cnt = getDiaryCnt();
		if(index < 0 || index >= cnt){
			return null;
		}
		return mDiaryList.get(index);
	}

	public Diary getDiary(String date){

		for (int i = 0; i < getDiaryCnt(); i++){
			String str = mDiaryList.get(i).getDate();
			if (date.equals(str)){
				return mDiaryList.get(i);
			}
		}
		return null;
	}

}
