package com.github.baocin.inclass07;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by aoi on 2/29/2016.
 */
public class Story implements Serializable{
    private long _id;
    String title;
    String byline;
    String abstractString;
    String created_date;
    String thumb_image_url;
    String normal_image_url;
    String category;

    public Story() {
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getByline() {
        return byline;
    }

    public void setByline(String byline) {
        this.byline = byline;
    }

    public String getAbstractString() {
        return abstractString;
    }

    public void setAbstractString(String abstractString) {
        this.abstractString = abstractString;
    }

    public String getThumb_image_url() {
        return thumb_image_url;
    }

    public void setThumb_image_url(String thumb_image_url) {
        this.thumb_image_url = thumb_image_url;
    }

    public String getNormal_image_url() {
        return normal_image_url;
    }

    public void setNormal_image_url(String normal_image_url) {
        this.normal_image_url = normal_image_url;
    }

    public String getCreated_date() {
        return created_date;
    }

    public void setCreated_date(String created_date) {
        this.created_date = created_date;
    }

    @Override
    public String toString() {
        return "Story{" +
                "_id=" + _id +
                ", title='" + title + '\'' +
                ", byline='" + byline + '\'' +
                ", abstractString='" + abstractString + '\'' +
                ", created_date='" + created_date + '\'' +
                ", thumb_image_url='" + thumb_image_url + '\'' +
                ", normal_image_url='" + normal_image_url + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}
