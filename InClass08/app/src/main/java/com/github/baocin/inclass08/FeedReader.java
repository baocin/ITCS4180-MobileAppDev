//File: FeedReader
//InClass 06
//2-22-2016
//Praveenkumar Sangalad
//Michael Pedersen
//Gabriel Lima

package com.github.baocin.inclass08;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by gbl on 2/22/2016.
 */
public class FeedReader {

    public static class NewsPullParser {

        static ArrayList<News> newsArrayList;
        static News news;

        static URL url;
        static HttpURLConnection connection;

        static int event;
        static XmlPullParser parser;

        static boolean insideItem = false;
        static boolean insideChannel = false;
        private static String category;

        static ArrayList<News> ParseNews (String URL) throws IOException, XmlPullParserException {

            newsArrayList = new ArrayList<>();

            url             = new URL(URL);
            connection      = (HttpURLConnection) url.openConnection();
            parser          = XmlPullParserFactory.newInstance().newPullParser();

            connection.connect();

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
//                parser.setFeature();
                parser.setInput(connection.getInputStream(), "UTF-8");

                event = parser.getEventType();
                while (event != XmlPullParser.END_DOCUMENT) {

                    switch (event) {
                        case XmlPullParser.START_TAG:
//                            Log.d("Name:", parser.getName());
                            if (parser.getName().equals("channel")) {
                                insideChannel = true;
                            }
                            else if (parser.getName().equals("title") && insideChannel && !insideItem){
                                category = parser.nextText().trim();
                            }

                            else if (parser.getName().equals("item") && insideChannel){
                                news = new News();
                                news.setCategory(category);
                                insideItem = true;
                            }

                            else if (parser.getName().equals("title") && insideItem) {
                                news.setStoryTitle(parser.nextText().trim());
                            }

                            else if (parser.getName().equals("media:thumbnail") && insideItem) {
                                String thumbUrl = parser.getAttributeValue(2).trim();
                                news.setThumbnail(thumbUrl);

                            }
                            break;

                        case XmlPullParser.END_TAG:
                            if (parser.getName().equals("item")) {
                                newsArrayList.add(news);
                                news = null;
                                insideItem = false;
                            }

                            break;
                    }

                    event = parser.next();
                }

            }





            return newsArrayList;

        }
    }
}
