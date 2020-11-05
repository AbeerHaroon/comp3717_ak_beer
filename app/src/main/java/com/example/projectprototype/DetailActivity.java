package com.example.projectprototype;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

public class DetailActivity extends AppCompatActivity {
    WebView news_detail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        initView();
        initData();
    }

    public void onHomeClick(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void initData() {
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        news_detail.loadUrl(url);
    }

    private void initView() {
        news_detail = findViewById(R.id.news_detail);
    }
}
