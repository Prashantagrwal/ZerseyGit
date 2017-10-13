package com.example.dell.zersey.Adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dell.zersey.POJO.CommentPojo;
import com.example.dell.zersey.R;

import java.util.ArrayList;

public class CommentDetailAdapter extends RecyclerView.Adapter<CommentDetailAdapter.ViewHolder> {

    ArrayList<CommentPojo> commentPojos;
    Context context;

public CommentDetailAdapter(Context context, ArrayList<CommentPojo> commentPojos){
    this.context=context;
    this.commentPojos=commentPojos;
}

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.tv_comment.setText(commentPojos.get(position).getUser_comment());
        holder.tv_name.setText(commentPojos.get(position).getUser_name());
    }

    @Override
    public int getItemCount() {
        return commentPojos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

       TextView tv_name,tv_comment;
        public ViewHolder(View itemView) {
            super(itemView);

            tv_name= (TextView) itemView.findViewById(R.id.tv_user_name);
            tv_comment= (TextView) itemView.findViewById(R.id.tv_user_comment);
        }
    }
}