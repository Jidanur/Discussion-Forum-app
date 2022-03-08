package com.example.simple_forum.controller.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.simple_forum.R;
import com.example.simple_forum.controller.managers.TopicManager;

public class TopicRecyclerAdapter extends RecyclerView.Adapter<TopicRecyclerAdapter.TopicViewHolder> {

    // Topic manager
    private TopicManager topic_manager;

    private OnTopicListener topic_listener;

    // Constructor
    public TopicRecyclerAdapter(TopicManager t_manager, OnTopicListener topic_listener){
        topic_manager =  t_manager;
        this.topic_listener = topic_listener;
    }

    @NonNull
    @Override
    public TopicRecyclerAdapter.TopicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        // Inflate view
        View item_view = LayoutInflater.from(parent.getContext()).inflate(R.layout.topic_list_items, parent, false);

        return new TopicViewHolder(item_view, topic_listener);
    }

    @Override
    public void onBindViewHolder(@NonNull TopicRecyclerAdapter.TopicViewHolder holder, int position) {

        // Set the topic title once
        String title = topic_manager.get(position).getTitle();
        holder.topic_title.setText(title);
    }

    @Override
    public int getItemCount() {
        return topic_manager.size();
    }

    // View holder class
    public class TopicViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView topic_title;
        OnTopicListener on_topic_listener;

        public TopicViewHolder(final View view, OnTopicListener t_listener){
            super(view);

            // Get text view
            topic_title = view.findViewById(R.id.topic_title);

            // Set topic listener
            this.on_topic_listener = t_listener;

            // Set on click listener
            view.setOnClickListener(this);

        }

        // On click listener
        @Override
        public void onClick(View view) {
            on_topic_listener.onTopicClick(getAdapterPosition());
        }
    }

    // On topic listener interface for topic items
    public interface OnTopicListener{
        void onTopicClick(int position);
    }
}
