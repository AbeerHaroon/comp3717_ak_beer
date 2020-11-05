package com.example.projectprototype;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsActivity extends AppCompatActivity {
    Button btn_local,btn_bc,btn_canada;
    ImageView news_image;
    int size;
    TextView news_title;
    List<Articles> articlesList = new ArrayList<>();
    Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {

            if (msg.what == Constant.SUCCESS){
                setData();
            }
        }
    };

    private void setData() {
        Random random = new Random();
        int nextInt = random.nextInt(size);
        final Articles articles = articlesList.get(nextInt);
        Glide.with(NewsActivity.this)
                .load(articles.getUrlToImage())
                .into(news_image);
        news_title.setText(articles.getTitle());
        news_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(NewsActivity.this,DetailActivity.class);
                intent.putExtra("url",articles.getUrl());
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        initView();
        initData();
        btn_local.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setData();
            }
        });
        btn_bc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setData();
            }
        });
        btn_canada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setData();
            }
        });

    }

    private void initData() {
        GetNews getNews = (GetNews) RetrofitUtil.getService(GetNews.class);
        Call<Data> call = getNews.getData();
        call.enqueue(new Callback<Data>() {
            @Override
            public void onResponse(Call<Data> call, Response<Data> response) {
                Data data = response.body();
                if (data.getStatus().equals("ok")){
                    size = data.getArticles().size();
                    articlesList.addAll(data.getArticles());
                    if (articlesList.size()==data.getArticles().size()){
                        handler.sendEmptyMessage(Constant.SUCCESS);
                    }

                }
            }

            @Override
            public void onFailure(Call<Data> call, Throwable t) {
                Toast.makeText(NewsActivity.this,"Failed to get News",Toast.LENGTH_LONG).show();
            }
        });
    }

    private void initView() {
        btn_local = findViewById(R.id.btn_local);
        btn_bc = findViewById(R.id.btn_bc);
        btn_canada = findViewById(R.id.btn_canada);
        news_image = findViewById(R.id.news_image);
        news_title = findViewById(R.id.news_title);
    }

    public void onHomeClick(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}