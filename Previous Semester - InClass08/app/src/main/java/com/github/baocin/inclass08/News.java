//File: News
//InClass08
//Group 18
//3-14-2016
//Praveenkumar Sangalad
//Michael Pedersen
//Gabriel Lima
package com.github.baocin.inclass08;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by aoi on 3/14/16.
 */

@DatabaseTable(tableName = "News")
public class News {
    @DatabaseField(generatedId = true, columnName = "id")
    public int id;

    @DatabaseField(columnName = "category")
    public String category;

    @DatabaseField(columnName = "storyTitle")
    public String storyTitle;


//    public Bitmap thumbnail;

    @DatabaseField(columnName = "thumbnail")
    public String thumbnail;

    public News() {
        //ORMLite needs this
    }

    @Override
    public String toString() {
        return "News{" +
                "id=" + id +
                ", category='" + category + '\'' +
                ", storyTitle='" + storyTitle + '\'' +
                ", thumbnail=" + thumbnail +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getStoryTitle() {
        return storyTitle;
    }

    public void setStoryTitle(String storyTitle) {
        this.storyTitle = storyTitle;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}
