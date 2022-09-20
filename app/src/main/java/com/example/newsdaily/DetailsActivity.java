package com.example.newsdaily;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.newsdaily.Models.Articles;
import com.squareup.picasso.Picasso;

public class DetailsActivity extends AppCompatActivity {
   Articles articles;
   TextView newstitle,newstime,newsauthor,newsdetails,newscontent;
   ImageView newsimage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        articles = (Articles) getIntent().getSerializableExtra("name");
        newstitle= findViewById(R.id.newTitle);
        newsauthor= findViewById(R.id.newsauthor);
        newstime= findViewById(R.id.newstime);
        newsdetails= findViewById(R.id.newsdetails);
        newscontent= findViewById(R.id.newscontent);
        newsimage= findViewById(R.id.newsimage);

//
               newstitle.setText(articles.getTitle());
               newsauthor.setText(articles.getAuthor());
               newstime.setText(articles.getPublishedAt());
               newsdetails.setText(articles.getDescription());
               newscontent.setText(articles.getContent());

        Picasso.get().load(articles.getUrlToImage()).into(newsimage);
    }
}