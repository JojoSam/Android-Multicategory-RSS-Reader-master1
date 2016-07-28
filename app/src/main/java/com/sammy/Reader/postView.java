package com.sammy.Reader;

import android.app.Activity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebSettings;
import android.webkit.WebViewClient;


public class postView extends Activity {
    private WebView webView;
 // private TextView title;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        webView = new WebView(this);
        this.setContentView(webView);
        Bundle bundle = this.getIntent().getExtras();

        String postContent = bundle.getString("content");
     //   String postTile= bundle.getString("title");

       // title=(TextView) findViewById(R.id.postTitle);
     //   title.setText(postTile);

        //webView = (WebView) this.findViewById(R.id.webView);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setDefaultZoom(WebSettings.ZoomDensity.FAR);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.setPadding(0, 0, 0, 0);

       webView.setWebViewClient(new WebViewClient());

       webView.loadUrl(postContent);

     // webView.loadData(postContent, "text/html", null);


    }
}
