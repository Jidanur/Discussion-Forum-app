package com.example.simple_forum.controller.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.simple_forum.R;
import com.example.simple_forum.models.Topic;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class TopicRecyclerAdapter extends RecyclerView.Adapter<TopicRecyclerAdapter.TopicViewHolder> {

    // Topic string names
    private ArrayList<Topic> topic_list;

    // TODO
    // Use array list of Topic class instead of array
    // Constructor
    public TopicRecyclerAdapter(ArrayList<Topic> topic_list){
        this.topic_list = topic_list;
    }

    @NonNull
    @Override
    public TopicRecyclerAdapter.TopicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        // Inflate view
        View item_view = LayoutInflater.from(parent.getContext()).inflate(R.layout.topic_list_items, parent, false);

        return new TopicViewHolder(item_view);
    }

    @Override
    public void onBindViewHolder(@NonNull TopicRecyclerAdapter.TopicViewHolder holder, int position) {

        // Set the discussion title once
        String title = topic_list.get(position).getTitle();
        holder.topic_title.setText(title);
    }

    @Override
    public int getItemCount() {
        return topic_list.size();
    }

    // View holder class
    public class TopicViewHolder extends RecyclerView.ViewHolder{

        private TextView topic_title;

        public TopicViewHolder(final View view){
            super(view);

            // Get text view
            topic_title = view.findViewById(R.id.topic_title);

        }

    }
}
