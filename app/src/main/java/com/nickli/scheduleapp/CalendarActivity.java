package com.nickli.scheduleapp;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class CalendarActivity extends AppCompatActivity {
    // Define variables
    private WebView webView;
    Button btnBack;

    @Override
    // When activity is created
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        // Sets frontend layout
        setContentView(R.layout.activity_calendar);
        webView = findViewById(R.id.wvCalendar);
        webView.setWebViewClient(new WebViewClient());
        // Uses webView to load an updated calendar provided by school
        webView.loadUrl("https://www.mkhs.org/apps/events/calendar/?id=0");
        // Implements zoom in and out controls for the activity
        webView.getSettings().setBuiltInZoomControls(true);

        btnBack = findViewById(R.id.btnBack);

        // OnClickListener to change activities
        btnBack.setOnClickListener(view -> {
            startActivity(new Intent(CalendarActivity.this, MainActivity.class));
        });
    }

    @Override
    // When Android back button pressed
    public void onBackPressed() {
        // Makes sure if webView can go back
        if(webView.canGoBack()){
            // Goes back in the website
            webView.goBack();
        }
        else {
            // If webView cannot go back, go to the previous activity in the app
            super.onBackPressed();
        }
    }
}
