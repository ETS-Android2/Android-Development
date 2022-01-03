package com.example.gpslocation;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

public class MainActivity extends AppCompatActivity{

    WebView simpleWebView;
    Button loadWebPage, loadFromStaticHtml;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // initiate buttons and a web view
        loadFromStaticHtml = (Button) findViewById(R.id.loadFromStaticHtml);
        loadWebPage = (Button) findViewById(R.id.loadWebPage);
        simpleWebView = (WebView) findViewById(R.id.simpleWebView);

        loadWebPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                simpleWebView.setWebViewClient(new MyWebViewClient());
                String url = "https://www.google.com/intl/en_in/gmail/about/";
                simpleWebView.getSettings().setJavaScriptEnabled(true);
                simpleWebView.loadUrl(url); // load a web page in a web view
            }
        });

        loadFromStaticHtml.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String customHtml = "<html><body><h1>Hello</h1>" +
                        "<h3>Welcome to the <em>restaurant</em></h3>" + "<p><mark>Buy 1 get 1 free</mark></p>" +
                        "<p>List of beverages</p>" + "<p><ol><li><b>Tea</b></li><li><u>Coffee</u></li><li>Milk</li></ol></p>" +
                        "<p><i>Have a good day</i></p>" + "<p><sup>Come</sup> back <sub>again</sub></p>" + "</body></html>";
                simpleWebView.loadData(customHtml, "text/html", "UTF-8"); // load html string data in a web view
            }
        });

    }

    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

}
