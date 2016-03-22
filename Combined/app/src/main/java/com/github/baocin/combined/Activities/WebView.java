//File: WebView
//Homework 04
//Group 18
//2-27-2016
//Praveenkumar Sangalad
//Michael Pedersen
//Gabriel Lima
package com.github.baocin.combined.Activities;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

public class WebView extends AppCompatActivity {
    android.webkit.WebView wv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        final String url = getIntent().getExtras().getString("url");

        wv = (android.webkit.WebView) findViewById(R.id.webview);
        wv.loadUrl(url);
        wv.setWebViewClient(new WebViewClient(){

            @Override
            public boolean shouldOverrideUrlLoading(android.webkit.WebView view, String url) {
                view.loadUrl(url);
                return true;

            }

            @Override
            public void onPageStarted(android.webkit.WebView view, String url, Bitmap favicon) {
                ((TextView)findViewById(R.id.urlBar)).setText(url);
                ((ImageView)findViewById(R.id.favicon)).setImageBitmap(favicon);
                super.onPageStarted(view, url, favicon);
            }

        });

    }
}
