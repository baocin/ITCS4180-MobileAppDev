package com.example.gbl.homework5.Utils;

import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by gbl on 3/3/2016.
 */
public class Utils {

    public static class HourlyXMLParser {

        public static ArrayList<Weather> parseWeather (InputStream in) throws XmlPullParserException, IOException {

            ArrayList<Weather> ret = new ArrayList<>();
            Weather weather = null;
            XmlPullParser parser;
            Boolean tempFlag = false;
            Boolean dewpointFlag = false;
            Boolean forecastFlag = false;
            Boolean wdspFlag = false;
            Boolean wdirFlag = false;
            Boolean mslpFlag = false;
            Boolean feelsLikeFlag = false;

            parser = XmlPullParserFactory.newInstance().newPullParser();
            parser.setInput(in, "UTF-8");

            int event = parser.getEventType();
            while (event != XmlPullParser.END_DOCUMENT) {

                switch (event) {
                    case XmlPullParser.START_TAG:

                        if (parser.getName().equals("forecast")) {
                            weather = new Weather();
                            forecastFlag = true;
                        }
                        else if (parser.getName().equals("civil") && forecastFlag) {
                            weather.setTime(parser.nextText().trim());
                        }
                        else if (parser.getName().equals("temp") && forecastFlag) {
                            tempFlag = true;
                        }
                        else if (parser.getName().equals("english") && tempFlag && forecastFlag) {
                            weather.setTemperature(parser.nextText().trim());
                        }
                        else if (parser.getName().equals("dewpoint") && forecastFlag) {
                            dewpointFlag = true;
                        }
                        else if (parser.getName().equals("english") && dewpointFlag && forecastFlag) {
                            weather.setDewpoint(parser.nextText().trim());
                        }
                        else if (parser.getName().equals("condition") && forecastFlag) {
                            weather.setClouds(parser.nextText().trim());
                        }
                        else if (parser.getName().equals("icon_url") && forecastFlag) {
                            weather.setIconUrl(parser.nextText().trim());
                        }
                        else if (parser.getName().equals("wspd") && forecastFlag) {
                            wdspFlag = true;
                        }
                        else if (parser.getName().equals("english") && wdspFlag && forecastFlag){
                            weather.setWindSpeed(parser.nextText().trim());
                        }
                        else if (parser.getName().equals("wdir") && forecastFlag) {
                            wdirFlag = true;
                        }else if (parser.getName().equals("dir") && forecastFlag && wdirFlag){
                            String windDirection = parser.nextText().trim();
                            Log.d("wdir", windDirection);
                            weather.setWindDirection(windDirection);
                        }else if (parser.getName().equals("degrees") && forecastFlag && wdirFlag){
                            String windDirection = parser.nextText().trim();
                            Log.d("wdir", windDirection);
                            weather.setWindDirection(windDirection + "° " + weather.getWindDirection());
                        }else if (parser.getName().equals("wx") && forecastFlag){
                            weather.setClimateType(parser.nextText().trim());
                        }
                        else if (parser.getName().equals("humidity") && forecastFlag) {
                            weather.setHumidity(parser.nextText().trim());
                        }
                        else if (parser.getName().equals("feelslike") && forecastFlag) {
                            feelsLikeFlag = true;
                        }
                        else if (parser.getName().equals("english") && feelsLikeFlag && forecastFlag){
                            weather.setFeelsLike(parser.nextText().trim());
                        }
                        else if (parser.getName().equals("mslp") && forecastFlag) {
                            mslpFlag = true;
                        }
                        else if (parser.getName().equals("english") && mslpFlag && forecastFlag){
                            weather.setPressure(parser.nextText().trim());
                        }

                        break;

                    case XmlPullParser.END_TAG:
                        if (parser.getName().equals("forecast")) {
                            ret.add(weather);
                            weather = null;
                            forecastFlag = false;
                        }
                        else if (parser.getName().equals("temp")) {
                            tempFlag = false;
                        }
                        else if (parser.getName().equals("dewpoint")) {
                            dewpointFlag = false;
                        }
                        else if (parser.getName().equals("feelslike")) {
                            feelsLikeFlag = false;
                        }
                        else if (parser.getName().equals("wspd")) {
                            wdspFlag = false;
                        }else if (parser.getName().equals("wdir")){
                            wdirFlag = false;
                        }else if (parser.getName().equals("mslp")) {
                            mslpFlag = false;
                        }

                        break;
                }

                event = parser.next();
            }
            return ret;
        }
    }
}
