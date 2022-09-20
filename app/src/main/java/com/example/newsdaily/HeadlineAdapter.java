package com.example.newsdaily;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsdaily.Models.Articles;
import com.squareup.picasso.Picasso;

import java.util.List;

public class HeadlineAdapter extends RecyclerView.Adapter<HeadlineAdapter.MyVieHolder> {
    private Context context;
    private List<Articles> articles;
    private  Selectlistner listner;

    public HeadlineAdapter(Context context, List<Articles> articles, Selectlistner listner) {
        this.context = context;
        this.articles = articles;
        this.listner = listner;
    }

    @NonNull
    @Override
    public MyVieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    return new MyVieHolder( LayoutInflater.from(context).inflate(R.layout.headline_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyVieHolder holder, int position) {
        holder.headline.setText(articles.get(position).getTitle());
        holder.source.setText(articles.get(position).getSource().getName());
        if(articles.get(position).getUrlToImage()!=null){
            Picasso.get().load(articles.get(position).getUrlToImage()).into(holder.image);
        }

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listner.onNewsClicked(articles.get(position));
            }
        });


    }

    public interface Selectlistner{
        public void onNewsClicked(Articles articles);
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public class MyVieHolder extends RecyclerView.ViewHolder {
       TextView headline,source;
       ImageView image;
       CardView cardView;
        public MyVieHolder(@NonNull View itemView) {
            super(itemView);
            headline=itemView.findViewById(R.id.headline);
            source=itemView.findViewById(R.id.source);
            image=itemView.findViewById(R.id.image_news);
            cardView=itemView.findViewById(R.id.cardview);

        }
    }
}
