//File: DatabaseManager
//InClass08
//Group 18
//3-14-2016
//Praveenkumar Sangalad
//Michael Pedersen
//Gabriel Lima
package com.github.baocin.inclass08;

/**
 * Created by aoi on 3/14/16.
 */
import java.sql.SQLException;
import java.util.List;

import android.content.Context;


public class DatabaseManager {

    static private DatabaseManager instance;

    static public void init(Context ctx) {
        if (instance == null) {
            instance = new DatabaseManager(ctx);
        }
    }

    static public DatabaseManager getInstance() {
        return instance;
    }

    private DatabaseHelper helper;
    private DatabaseManager(Context ctx) {
        helper = new DatabaseHelper(ctx);
    }

    private DatabaseHelper getHelper() {
        return helper;
    }

    public List<News> getAllNews() {
        List<News> favorites = null;
        try {
            favorites = getHelper().getNewsDao().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return favorites;
    }

    public void createNews(News n){
        try {
            getHelper().getNewsDao().create(n);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeNews(News news) {
        try {
            getHelper().getNewsDao().delete(news);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
//    public void removeNews(int id){
//        try {
//            getHelper().getNewsDao().create(n);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

}