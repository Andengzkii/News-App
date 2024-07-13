package com.project.newsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.newsapp.Models.NewsHeadlines;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomViewHolder> {
    private Context context;
    private List<NewsHeadlines> headlines;
    private SelectListener listener;

    public CustomAdapter(Context context, List<NewsHeadlines> headlines, SelectListener listener) {
        this.context = context;
        this.headlines = headlines;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.headline_list_items, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        NewsHeadlines headline = headlines.get(position);

        holder.text_title.setText(headline.getTitle());
        holder.text_source.setText(headline.getSource().getName());

        // Load image using Picasso
        if (headline.getUrlToImage() != null && !headline.getUrlToImage().isEmpty()) {
            Picasso.get()
                    .load(headline.getUrlToImage())
                    .placeholder(R.drawable.not_available) // Placeholder image
                    .error(R.drawable.not_available) // Error image
                    .into(holder.img_headline);
        } else {
            holder.img_headline.setImageResource(R.drawable.not_available);
        }

        holder.cardView.setOnClickListener(v -> listener.OnNewsClicked(headline));
    }

    @Override
    public int getItemCount() {
        return headlines.size();
    }
}
