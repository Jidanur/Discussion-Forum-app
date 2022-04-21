package com.example.simple_forum.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.simple_forum.R;
import com.example.simple_forum.controller.managers.CommentManager;
import com.example.simple_forum.models.Comment;

import java.util.ArrayList;

public class CommentRecyclerAdapter extends RecyclerView.Adapter<CommentRecyclerAdapter.CommentViewHolder>{

    //Comment manager
    private CommentManager com_manager;
    private ArrayList<Comment> queryset;

    public CommentRecyclerAdapter(CommentManager c_manager, String disc_title){//, OnCommentListener newListener){
        com_manager = c_manager;

        // Set com_manager queryset based on discussion
        queryset = com_manager.filter(disc_title);
    }

    @NonNull
    @Override
    public CommentRecyclerAdapter.CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item_view = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_list_items, parent, false);

        return new CommentViewHolder(item_view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentViewHolder holder, int position) {

        //Get obj
        Comment com = ((Comment) queryset.get(position));

        // Set the comment title
        String comment_text = com.getContent();
        String comment_user = com.getUser().getUsername();
        String comment_date = com.getDate();

        holder.comment_text.setText(comment_text);
        holder.username_text.setText(comment_user);
        holder.date_text.setText(comment_date);
    }

    @Override
    public int getItemCount() {
        return queryset.size();
    }

    //Comment view holder
    public class CommentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView comment_text;
        private TextView username_text;
        private TextView date_text;

        private CommentViewHolder(final View view){
            super(view);
            comment_text = view.findViewById(R.id.com_content);
            username_text = view.findViewById(R.id.com_username);
            date_text = view.findViewById(R.id.com_date);
        }

        @Override
        public void onClick(View view) {

        }
    }
}
