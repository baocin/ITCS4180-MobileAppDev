//File: WebView
//Homework 04
//Group 18
//2-27-2016
//Praveenkumar Sangalad
//Michael Pedersen
//Gabriel Lima
package com.github.baocin.homework04;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebViewClient;

public class WebView extends AppCompatActivity {
    android.webkit.WebView wv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        final String url = getIntent().getExtras().getString("url");

        wv = (android.webkit.WebView) findViewById(R.id.webview);
        wv.loadUrl(url);
        wv.setWebViewClient(new WebViewClient());

    }
}
