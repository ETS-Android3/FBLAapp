package com.nickli.scheduleapp;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class CalendarActivity extends AppCompatActivity {
    private WebView webView;
    Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        webView = findViewById(R.id.wvCalendar);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://www.mkhs.org/apps/events/calendar/?id=0");
        webView.getSettings().setBuiltInZoomControls(true);

        btnBack = findViewById(R.id.btnBack);

        btnBack.setOnClickListener(view -> {
            startActivity(new Intent(CalendarActivity.this, MainActivity.class));
        });
    }

    @Override
    public void onBackPressed() {
        if(webView.canGoBack()){
            webView.goBack();
        }
        else {
            super.onBackPressed();
        }
    }
}
