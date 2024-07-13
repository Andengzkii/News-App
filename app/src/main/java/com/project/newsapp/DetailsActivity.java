package com.project.newsapp;

import android.os.Bundle;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.appcompat.app.AppCompatActivity;

public class DetailsActivity extends AppCompatActivity {
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        String url = getIntent().getStringExtra("url");
        Log.d("DetailsActivity", "Received URL: " + url);  // Log the URL to verify

        if (url != null && !url.isEmpty()) {
            webView = findViewById(R.id.web_view);
            WebSettings webSettings = webView.getSettings();
            webSettings.setJavaScriptEnabled(true);
            webView.setWebViewClient(new WebViewClient() {
                @Override
                public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                    Log.e("WebViewError", "Error: " + description);
                    super.onReceivedError(view, errorCode, description, failingUrl);
                }
            });
            webView.loadUrl(url);
        } else {
            Log.e("DetailsActivity", "URL is null or empty");
        }
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}
