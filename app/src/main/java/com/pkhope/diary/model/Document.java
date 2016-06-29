package com.pkhope.diary.model;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import android.content.Context;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.FindCallback;
import com.pkhope.diary.LoadDataListener;

public class Document {

	private static final String TAG = "Document";
	public static final String FILE_NAME = "diary.dat";
	
	private Context mContext;
    private DiaryManager mDiaryManager;
    
    public Document(Context context) throws IOException {
		mContext = context;
		mDiaryManager = new DiaryManager();
//    	readFile();
    }

	public void deleteFile() {

		mContext.deleteFile(FILE_NAME);
	}
    
    public DiaryManager getDiaryManager() {
    	return mDiaryManager;
    }

	public void load(final LoadDataListener listener){
		AVUser user = AVUser.getCurrentUser();
		AVQuery<DiaryLc> query = AVObject.getQuery(DiaryLc.class);
		query.whereEqualTo("userName",user.getUsername());
		query.limit(100);
		query.findInBackground(new FindCallback<DiaryLc>() {
			@Override
			public void done(List<DiaryLc> list, AVException e) {
				for (DiaryLc dl : list){
                    Diary diary =  mDiaryManager.createDiary(dl.getDate(),dl.getWeek(),dl.getContent(),false);
					mDiaryManager.addDairy(diary);
				}

				listener.succeed();
			}
		});
	}
    
	public void readFile() throws IOException {

		try{
            FileInputStream fin = mContext.openFileInput(FILE_NAME);
            ObjectInputStream objIn = new ObjectInputStream(fin);
            String str = (String) objIn.readObject();
            int cnt = Integer.parseInt(str);
            for(int i = 0; i < cnt; i++) {
            	Diary diary = (Diary)objIn.readObject();
				mDiaryManager.addDairy(diary);
            }
            objIn.close();

		}
		catch(Exception e) {
			e.printStackTrace();
		}
	    
	}
	
	public void writeFile() throws IOException {
		
	    try{
	    	
			FileOutputStream fout = mContext.openFileOutput(FILE_NAME,mContext.MODE_PRIVATE);
			ObjectOutputStream objOut = new ObjectOutputStream(fout);
			int cnt = mDiaryManager.getDiaryCnt();
            String str = Integer.toString(cnt);
			objOut.writeObject(str);
            for(int i = 0; i < cnt; i++) {
            	Diary diary = mDiaryManager.getDiary(i);
    			objOut.writeObject(diary);
            }
			objOut.close();
			
	    }
	    catch(Exception e) {
	    	
	    	e.printStackTrace();
	    	
	    }
	}
}
