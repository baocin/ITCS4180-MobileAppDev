//File: City
//Homework 05
//Group 18
//3-08-2016
//Praveenkumar Sangalad
//Michael Pedersen
//Gabriel Lima
package com.example.gbl.homework5.Utils;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.gbl.homework5.Tasks.CheckValidLocationTask;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Created by gbl on 3/3/2016.
 */
public class City implements Serializable{

    String name;
    String state;
    String maxTemp;
    String minTemp;
    ArrayList<Weather> hourlyWeather;

    public City(String name, String state){
        name = name.replace(" ", "_");      //Directions said to store name with underscores.
        setName(name);
        setState(state);
    }

    public void updateMinMaxTemperature(){

        maxTemp = hourlyWeather.get(0).getTemperature();
        minTemp = hourlyWeather.get(0).getTemperature();

        for (Weather w : hourlyWeather){

            int temp = Integer.parseInt(w.getTemperature());

            if (Integer.parseInt(maxTemp) < temp){
                maxTemp = Integer.toString(temp);
            }

            if (Integer.parseInt(minTemp) > temp) {
                minTemp = Integer.toString(temp);
            }
        }




    }

    public void setHourlyWeather (ArrayList<Weather> w) {
        this.hourlyWeather = w;
        updateMinMaxTemperature();
    }

    public ArrayList<Weather> getHourlyWeather() {
        return hourlyWeather;
    }

    public void setName(String name) {
        this.name = name.replace(" ", "_");
    }

    public void setState(String state) {
        this.state = state.toUpperCase();
    }

    @Override
    public String toString() {
        return name.replace("_", " ") + ", " + state;
    }

    /** Auxiliar Functions **/

    public static boolean isValidLocation(String cityName, String stateCode) {
        if (stateCode.length() != 2) {  return false;  }

        Boolean isExistingCity = true;

        try {
            isExistingCity = new CheckValidLocationTask().execute(cityName, stateCode).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return isExistingCity;
    }

    /** Default Gets/Sets **/
    public String getName() {
        return name;
    }
    public String getState() {
        return state;
    }

    public String getMaxTemp() {
        return maxTemp;
    }

    public String getMinTemp() {
        return minTemp;
    }




}
