//File: RequestParams
//INCLAS 07
//Group 18
//2-27-2016
//Praveenkumar Sangalad
//Michael Pedersen
//Gabriel Lima
package com.github.baocin.inclass07;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;

/**
 * Created by aoi on 2/24/2016.
 */
public class RequestParams {
    String baseUrl;
    HashMap<String, String> params = new HashMap<String, String>();

    public RequestParams(String url){
        baseUrl = url;
    }

    public void addParam(String key, String value){
        params.put(key, value);
    }

    public String getEncodedParams(){
        StringBuilder sb = new StringBuilder();
        sb.append("?");
        for (String key : params.keySet()){
            try {
                String encodedKey = URLEncoder.encode(key, "UTF-8");
                String encodedValue = URLEncoder.encode(params.get(key), "UTF-8");
                if (sb.length() > 0){
                    sb.append("&");
                }
                sb.append(encodedKey + "=" + encodedValue);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    public String getEncodedURL(){
        return this.baseUrl + getEncodedParams();
    }

    @Override
    public String toString() {
        return "RequestParams{" +
                "baseUrl='" + baseUrl + '\'' +
                ", params=" + params +
                '}';
    }
}