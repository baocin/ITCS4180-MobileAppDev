package com.github.baocin.homework03;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.HashMap;

/**
 * Created by aoi on 2/20/16.
 */
public class Question implements Parcelable {
    int id;
    String questionText;
    HashMap<String, Integer> options;
    String imageURL;

    protected Question(Parcel in) {
        id = in.readInt();
        questionText = in.readString();
        imageURL = in.readString();
        options = (HashMap<String, Integer>) in.readBundle().getSerializable("options");
    }

    public static final Creator<Question> CREATOR = new Creator<Question>() {
        @Override
        public Question createFromParcel(Parcel in) {
            return new Question(in);
        }

        @Override
        public Question[] newArray(int size) {
            return new Question[size];
        }
    };

    public Question() {
        id = 0;
        questionText = "";
        options = new HashMap<String, Integer>();
        imageURL = "";
    }

    @Override
    public int describeContents() {
        return 0;       //Nothing special about this parcelable!
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeInt(id);
        dest.writeString(questionText);
        dest.writeString(imageURL);

        Bundle bun = new Bundle();
        bun.putSerializable("options", options);
        dest.writeBundle(bun);

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public HashMap<String, Integer> getOptions() {
        return options;
    }

    public void setOptions(HashMap<String, Integer> options) {
        this.options = options;
    }

    public void setOption(String question, Integer value){
        this.options.put(question, value);
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
