package com.github.baocin.inclass07;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by aoi on 3/14/16.
 */
public class StoryDAO {
    private SQLiteDatabase db;
    public StoryDAO(SQLiteDatabase db){
        this.db = db;

    }



    public long save(Story n){
        ContentValues values = new ContentValues();
        values.put(StoryTable.STORY_TITLE, n.getTitle());
        values.put(StoryTable.STORY_BYLINE, n.getByline());
        values.put(StoryTable.STORY_ABSTRACT_STRING, n.getAbstractString());
        values.put(StoryTable.STORY_CREATE_DATE, n.getCreated_date());
        values.put(StoryTable.STORY_THUMB_URL, n.getThumb_image_url());
        values.put(StoryTable.STORY_NORMAL_URL, n.getNormal_image_url());
        values.put(StoryTable.STORY_CATEGORY, n.getCategory());

        return db.insert(StoryTable.TABLE_NAME, null, values);
    }

    public boolean update(Story n){
        ContentValues values = new ContentValues();
        values.put(StoryTable.STORY_TITLE, n.getTitle());
        values.put(StoryTable.STORY_BYLINE, n.getByline());
        values.put(StoryTable.STORY_ABSTRACT_STRING, n.getAbstractString());
        values.put(StoryTable.STORY_CREATE_DATE, n.getCreated_date());
        values.put(StoryTable.STORY_THUMB_URL, n.getThumb_image_url());
        values.put(StoryTable.STORY_NORMAL_URL, n.getNormal_image_url());
        values.put(StoryTable.STORY_CATEGORY, n.getCategory());
        return db.update(StoryTable.TABLE_NAME, values, StoryTable.STORY_ID + "=" + n.get_id(), null) > 0;
    }

    public boolean delete(Story n){
        return db.delete(StoryTable.TABLE_NAME, StoryTable.STORY_ID + "=" + n.get_id(), null) > 0;
    }

    public Story get(long id){
        Story n = null;
        Cursor c = db.query(true, StoryTable.TABLE_NAME,
                new String[]{StoryTable.STORY_ID, StoryTable.STORY_TITLE, StoryTable.STORY_BYLINE, StoryTable.STORY_ABSTRACT_STRING,
                        StoryTable.STORY_CREATE_DATE, StoryTable.STORY_THUMB_URL, StoryTable.STORY_NORMAL_URL, StoryTable.STORY_CATEGORY}
                ,StoryTable.STORY_ID + "=" + n.get_id(), null, null, null, null, null);
        if (c != null){
            c.moveToFirst();
            n = this.buildStoryFromCursor(c);
        }
        if (!c.isClosed()){
            c.close();
        }
        return n;

    }

    public List<Story> getAll(){
        List<Story> list = new ArrayList<Story>();
        Cursor c = db.query(StoryTable.TABLE_NAME, new String[]{StoryTable.STORY_ID, StoryTable.STORY_TITLE, StoryTable.STORY_BYLINE, StoryTable.STORY_ABSTRACT_STRING,
                StoryTable.STORY_CREATE_DATE, StoryTable.STORY_THUMB_URL, StoryTable.STORY_NORMAL_URL,StoryTable.STORY_CATEGORY}
                , null, null, null, null, null);
        if (c != null){
            c.moveToFirst();
            do {
                Story n = this.buildStoryFromCursor(c);
                if (n != null){
                    list.add(n);

                }
            }while(c.moveToNext());
        }
        if (!c.isClosed()){
            c.close();
        }
        return list;
    }

    public List<Story> getAll(String category){
        List<Story> list = new ArrayList<Story>();
        Cursor c = db.query(StoryTable.TABLE_NAME, new String[]{StoryTable.STORY_ID, StoryTable.STORY_TITLE, StoryTable.STORY_BYLINE, StoryTable.STORY_ABSTRACT_STRING,
                StoryTable.STORY_CREATE_DATE, StoryTable.STORY_THUMB_URL, StoryTable.STORY_NORMAL_URL,StoryTable.STORY_CATEGORY}
                ,StoryTable.STORY_CATEGORY + "=" + category, null, null, null, null);
        if (c != null){
            c.moveToFirst();
            do {
                Story n = this.buildStoryFromCursor(c);
                if (n != null){
                    list.add(n);

                }
            }while(c.moveToNext());
        }
        if (!c.isClosed()){
            c.close();
        }
        return list;
    }

    public boolean removeAll(String category){
        return db.delete(StoryTable.TABLE_NAME, StoryTable.STORY_CATEGORY + "=" + category, null) > 0;
    }

    private Story buildStoryFromCursor(Cursor c){
        Story n = null;
        if (c != null){
            n = new Story();
            n.set_id(c.getLong(0));
            n.setTitle(c.getString(1));
            n.setByline(c.getString(2));
            n.setAbstractString(c.getString(3));

            n.setCreated_date(
                    c.getString(4)
            );
            n.setThumb_image_url(c.getString(5));
            n.setNormal_image_url(c.getString(6));
            n.setCategory(c.getString(7));
        }
        return n;
    }
}
