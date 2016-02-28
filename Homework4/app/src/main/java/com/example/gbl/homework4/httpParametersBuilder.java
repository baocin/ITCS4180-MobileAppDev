package com.example.gbl.homework4;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;

/**
 * Created by gbl on 2/26/2016.
 */
public class httpParametersBuilder {

    private String baseURL;
    private String valueBuffer;
    private StringBuilder buffer;
    private HashMap<String, String> parameters;

    public httpParametersBuilder(String baseURL) {

        super();
        this.baseURL = baseURL;
        parameters = new HashMap<>();
    }

    public void addParameters(String param, String value) {
        parameters.put(param, value);
    }

    private String setParameters() {

        buffer = new StringBuilder();

        for (String key : parameters.keySet()) {

            try {

                valueBuffer = URLEncoder.encode(parameters.get(key), "UTF-8");
                if (buffer.length() > 0) {
                    buffer.append("&");
                }

                buffer.append(key + "=" + valueBuffer);


            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        return buffer.toString();
    }

    public String getParametersURL() {
        return baseURL + "?" + setParameters();
    }
}
