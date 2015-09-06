package com.pkhope.diary;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import android.content.Context;

import com.pkhope.diary.DiaryManager;

public class Document {
	
	private Context mContext;
    private DiaryManager mDiaryManager;
    
    public Document(Context context) throws IOException {
		mContext = context;
		mDiaryManager = new DiaryManager();
    	readFile("diary.dat");
    }
    
    public DiaryManager getDiaryManager() {
    	return mDiaryManager;
    }
    
	public void readFile(String fileName) throws IOException {
		
		try{
			
		    File file = mContext.getFileStreamPath(fileName);
		    if(file == null || !file.exists()) {
		        return ;
		    }
            FileInputStream fin = mContext.openFileInput(fileName);
            ObjectInputStream objIn = new ObjectInputStream(fin);
            String str = (String) objIn.readObject();
            int cnt = Integer.parseInt(str);
            for(int i = 0; i < cnt; i++) {
            	Diary diary = (Diary)objIn.readObject();
				mDiaryManager.addDairy(diary);
            }
            objIn.close();
            System.out.println("1-->>"+str);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	    
	}
	
	public void writeFile(String fileName) throws IOException {
		
	    try{
	    	
			FileOutputStream fout = mContext.openFileOutput(fileName, 0);
			ObjectOutputStream objOut = new ObjectOutputStream(fout);
			int cnt = mDiaryManager.getDiaryCnt();
            String str = Integer.toString(cnt);
			objOut.writeObject(str);
            for(int i = 0; i < cnt; i++) {
            	Diary diary = mDiaryManager.getDiary(i);
    			objOut.writeObject(diary);
            }
			objOut.close();
            System.out.println("1-->>"+cnt);
            System.out.println("2-->>"+str); 
			
	    }
	    catch(Exception e) {
	    	
	    	e.printStackTrace();
	    	
	    }
	}
}
