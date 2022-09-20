package com.example.newsdaily;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.newsdaily.Models.Articles;
import com.example.newsdaily.Models.NewsApiResponse;

import java.util.List;

public class MainActivity extends AppCompatActivity implements HeadlineAdapter.Selectlistner , View.OnClickListener{
    RecyclerView recyclerView;
    HeadlineAdapter adapter;
    ProgressDialog dialog;
    Button b1,b2,b3,b4,b5,b6,b7;
    SearchView searchView;
//    HeadlineAdapter.Selectlistner clicklistner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b1=findViewById(R.id.btn_1);
        b1.setOnClickListener(this);

        b2=findViewById(R.id.btn_2);
        b2.setOnClickListener(this);

        b3=findViewById(R.id.btn_3);
        b3.setOnClickListener(this);

        b4=findViewById(R.id.btn_4);
        b4.setOnClickListener(this);

        b5=findViewById(R.id.btn_5);
        b5.setOnClickListener(this);

        b6=findViewById(R.id.btn_6);
        b6.setOnClickListener(this);

        b7=findViewById(R.id.btn_7);
        b7.setOnClickListener(this);

        searchView=findViewById(R.id.searchview);

        recyclerView=findViewById(R.id.recyclerview);
        dialog = new ProgressDialog(this);
        dialog.setTitle("Fetching News Articles");
        dialog.show();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                dialog.setTitle("Fetching realted articles");
                dialog.show();
                RequestManager manager= new RequestManager(MainActivity.this);
                manager.getNewsHeadlines(listner,"general",query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        RequestManager manager= new RequestManager(this);
        manager.getNewsHeadlines(listner,"general",null);
    }

    private final  OnFetchDataListner<NewsApiResponse> listner =new OnFetchDataListner<NewsApiResponse>() {
        @Override
        public void onFetchData(List<Articles> list, String message) {
            if (list.isEmpty()){

                Toast.makeText(MainActivity.this, "No Data Found", Toast.LENGTH_SHORT).show();
            }else{
              shownews(list);
              dialog.dismiss();}
        }

        @Override
        public void onError(String message) {
                     System.out.println("we have some error");
            Toast.makeText(MainActivity.this, "error is "+message, Toast.LENGTH_SHORT).show();
        }
    };

    private void shownews(List<Articles> list) {
        recyclerView=findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this,1));
        adapter=new HeadlineAdapter(this,list,this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onNewsClicked(Articles articles) {
        startActivity(new Intent(MainActivity.this,DetailsActivity.class).putExtra("name",articles));

    }

    @Override
    public void onClick(View view) {
        Button button=(Button) view;
        String category=button.getText().toString();
        dialog.setTitle("Fetching News Articles of"+category);
        dialog.show();
        RequestManager manager= new RequestManager(this);
        manager.getNewsHeadlines(listner,category,null);

    }
}