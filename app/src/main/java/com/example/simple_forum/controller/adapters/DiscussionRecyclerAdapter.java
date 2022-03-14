package com.example.simple_forum.controller.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.simple_forum.R;
import com.example.simple_forum.controller.managers.DiscussionManager;
import com.example.simple_forum.models.Discussion;

import java.util.ArrayList;

public class DiscussionRecyclerAdapter extends RecyclerView.Adapter<DiscussionRecyclerAdapter.DiscussionViewHolder> {

    // Discussion manager
    private DiscussionManager disc_manager;
    private ArrayList<Discussion> queryset;

    // Constructor
    public DiscussionRecyclerAdapter(DiscussionManager d_manager, String topic_title){
        disc_manager = d_manager;

        // Set disc_manager queryset based on topic
        queryset = disc_manager.filter(topic_title);

    }

    @NonNull
    @Override
    public DiscussionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        // Inflate view
        View item_view = LayoutInflater.from(parent.getContext()).inflate(R.layout.discussion_list_items, parent, false);

        return new DiscussionViewHolder(item_view);
    }

    @Override
    public void onBindViewHolder(@NonNull DiscussionViewHolder holder, int position) {

        // Get obj
        Discussion disc = ((Discussion) queryset.get(position));

        // Set the discussion title
        String disc_title = disc.getTitle();
        holder.disc_title.setText(disc_title);
    }

    @Override
    public int getItemCount() {
        return queryset.size();
    }

    // Discussion view holder
    public class DiscussionViewHolder extends RecyclerView.ViewHolder {

        private TextView disc_title;

        public DiscussionViewHolder(final View view) {
            super(view);

            // Get the discussion title
            disc_title = view.findViewById(R.id.discussion_title);
        }
    }
}
