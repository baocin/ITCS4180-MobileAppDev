package com.example.gbl.homework5.Utils;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by gbl on 3/3/2016.
 */
public class Weather implements Comparable<Weather>, Serializable{

    private String time;
    private String temperature;
    private String dewpoint;
    private String clouds;
    private String iconUrl;
    private String windSpeed;
    private String windDirection;
    private String climateType;
    private String humidity;
    private String feelsLike;
    private String pressure;

    @Override
    public int compareTo(Weather another) {

        SimpleDateFormat parser = new SimpleDateFormat("hh:mm aa");
        int ret = 0;
        
        try {

            Date thisTime = parser.parse(this.time);
            Date anotherTime = parser.parse(another.time);

            if (thisTime.before(anotherTime)) {
                ret = -1;
            }
            else if (thisTime.after(anotherTime)) {
                ret = 1;
            }
            else {
                ret = 0;
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return ret;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "time='" + time + '\'' +
                ", temperature='" + temperature + '\'' +
                ", dewpoint='" + dewpoint + '\'' +
                ", clouds='" + clouds + '\'' +
                ", iconUrl='" + iconUrl + '\'' +
                ", windSpeed='" + windSpeed + '\'' +
                ", windDirection='" + windDirection + '\'' +
                ", climateType='" + climateType + '\'' +
                ", humidity='" + humidity + '\'' +
                ", feelsLike='" + feelsLike + '\'' +
                ", pressure='" + pressure + '\'' +
                '}';
    }

    /** Default gets/sets **/
    public String getPressure() {
        return pressure;
    }
    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getFeelsLike() {
        return feelsLike;
    }

    public void setFeelsLike(String feelsLike) {
        this.feelsLike = feelsLike;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getClimateType() {
        return climateType;
    }

    public void setClimateType(String climateType) {
        this.climateType = climateType;
    }

    public String getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(String windDirection) {
        this.windDirection = windDirection;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getClouds() {
        return clouds;
    }

    public void setClouds(String clouds) {
        this.clouds = clouds;
    }

    public String getDewpoint() {
        return dewpoint;
    }

    public void setDewpoint(String dewpoint) {
        this.dewpoint = dewpoint;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }


}
