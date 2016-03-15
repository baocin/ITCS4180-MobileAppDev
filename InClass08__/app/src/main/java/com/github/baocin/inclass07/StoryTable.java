package com.github.baocin.inclass07;

import android.database.sqlite.SQLiteDatabase;

import java.util.Date;

/**
 * Created by aoi on 3/14/16.
 */
public class StoryTable {
    static final String TABLE_NAME = "stories";
    static final String STORY_ID = "_id";
    static final String STORY_TITLE = "title";
    static final String STORY_BYLINE = "byline";
    static final String STORY_ABSTRACT_STRING = "abstractString";
    static final String STORY_CREATE_DATE = "createDate";
    static final String STORY_THUMB_URL = "thumbURL";
    static final String STORY_NORMAL_URL = "normalURL";
    static final String STORY_CATEGORY = "category";

    static public void onCreate(SQLiteDatabase db){
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE " + StoryTable.TABLE_NAME + "(");
        sb.append(StoryTable.STORY_ID + " integer primary key autoincrement,");
        sb.append(StoryTable.STORY_TITLE + " text not null,");
        sb.append(StoryTable.STORY_BYLINE + " text not null,");
        sb.append(StoryTable.STORY_ABSTRACT_STRING + " text not null,");
        sb.append(StoryTable.STORY_CREATE_DATE + " date not null,");
        sb.append(StoryTable.STORY_THUMB_URL + " text not null,");
        sb.append(StoryTable.STORY_NORMAL_URL + " text not null,");
        sb.append(StoryTable.STORY_CATEGORY + " text not null");
        sb.append(");");
        db.execSQL(sb.toString());
    }

    static public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + StoryTable.TABLE_NAME);
        StoryTable.onCreate(db);
    }
}
