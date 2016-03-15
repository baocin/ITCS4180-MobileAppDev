package com.github.baocin.inclass07;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

/**
 * Created by aoi on 3/14/16.
 */
public class DataManager {
    Context mContext;
    DatabaseHelper dbOpenHelper;
    SQLiteDatabase db;
    StoryDAO storyDao;

    public DataManager(Context mContext){
        this.mContext = mContext;
        dbOpenHelper = new DatabaseHelper(mContext);
        db = dbOpenHelper.getWritableDatabase();
        storyDao = new StoryDAO(db);

    }
    public void close(){
        db.close();
    }

    public long saveStory(Story n){
        return storyDao.save(n);
    }

    public boolean updateStory(Story n){
        return storyDao.update(n);
    }

    public boolean deleteStory(Story n){
        return storyDao.delete(n);
    }

    public Story getStory(long id){
        return storyDao.get(id);
    }

    public List<Story> getAllStories(String cat) {return storyDao.getAll(cat);}
    public boolean removeAllStories(String cat) {return storyDao.removeAll(cat);}

    public List<Story> getAllStories(){
        return storyDao.getAll();
    }


}
