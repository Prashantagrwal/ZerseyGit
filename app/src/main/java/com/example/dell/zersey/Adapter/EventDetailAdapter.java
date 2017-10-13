package com.example.dell.zersey.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dell.zersey.Event.AddComment;
import com.example.dell.zersey.Event.Comment;
import com.example.dell.zersey.Firebase.FirebaseWork;
import com.example.dell.zersey.POJO.EventDetailClass;
import com.example.dell.zersey.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class EventDetailAdapter extends RecyclerView.Adapter<EventDetailAdapter.ViewHolder> {

    ArrayList<EventDetailClass> android;
     Context context;
    String google_id;


    public EventDetailAdapter(Context context, ArrayList<EventDetailClass> android,String google_id) {
        this.android = android;
        this.context = context;
        this.google_id=google_id;

    }


        @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.event_show, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position)
    {

        holder.b_like.setEnabled(true);
        holder.tv_like.setText("NA");
        holder.tv_comment.setText("NA");
       final FirebaseWork firebaseWork= new FirebaseWork();

           firebaseWork.TotalLiked(android.get(position).getEvent_id(),new ValueEventListener() {
               @Override
               public void onDataChange(DataSnapshot dataSnapshot) {
                   if(dataSnapshot.exists())
                   {
                               long count = dataSnapshot.getChildrenCount();
                               Log.e("like",String.valueOf(count));
                               holder.tv_like.setText(String.valueOf(count));
                           }
                       }
               @Override
               public void onCancelled(DatabaseError databaseError) {

               }
           });

        firebaseWork.TotalComment(android.get(position).getEvent_id(),new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                {
                    long count = dataSnapshot.getChildrenCount();
                    holder.tv_comment.setText(String.valueOf(count));
                   /* for (DataSnapshot data : dataSnapshot.getChildren()) {
                        if (data.exists()) {

                            long count = data.getChildrenCount();
                            holder.tv_comment.setText(String.valueOf(count));
                        }
                    }
                    */}}
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


           firebaseWork.isLiked(android.get(position).getEvent_id(), google_id, new ValueEventListener() {
               @Override
               public void onDataChange(DataSnapshot dataSnapshot) {
                           boolean is_liked=false;
                   if(dataSnapshot.exists())
                       is_liked=true;

                   if (is_liked){
                       holder.b_like.setEnabled(false);
                       holder.b_like.setText("LIKED...");
                   }
               }

               @Override
               public void onCancelled(DatabaseError databaseError) {

               }
           });


            holder.tv_category.setText(android.get(position).getCategory());
          holder.tv_title.setText(android.get(position).getTitle());
        holder.tv_date.setText(android.get(position).getEvent_date()+" "+android.get(position).getEvent_time());

holder.linearLayout.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent intent=new Intent(context,Comment.class);
        intent.putExtra("category",android.get(position).getCategory());
        intent.putExtra("title",android.get(position).getTitle());
        intent.putExtra("date",android.get(position).getEvent_date()+" "+android.get(position).getEvent_time());
        intent.putExtra("desp",android.get(position).getDesp());
        intent.putExtra("image_url",android.get(position).getImage_url());
        intent.putExtra("video_url",android.get(position).getVideo_url());
        context.startActivity(intent);
    }
});

        holder.b_like.setText("LIKE");
holder.b_like.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        firebaseWork.like_event(android.get(position).getEvent_id(),google_id);
        holder.b_like.setEnabled(false);
        holder.b_like.setText("LIKED...");
    }
});

holder.b_comment.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent intent =new Intent(context, AddComment.class);
        intent.putExtra("event_id",android.get(position).getEvent_id());
        intent.putExtra("category",android.get(position).getCategory());
        intent.putExtra("title",android.get(position).getTitle());
        intent.putExtra("date",android.get(position).getEvent_date()+" "+android.get(position).getEvent_time());
        intent.putExtra("desp",android.get(position).getDesp());
        context.startActivity(intent);
    }
});

    }


    @Override
    public int getItemCount() {
        return android.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder  {

        private TextView tv_category, tv_title, tv_date, tv_time, tv_like, tv_comment;
        private Button b_like, b_comment;
        private LinearLayout linearLayout;
        public ViewHolder(View view) {
            super(view);
            linearLayout= (LinearLayout) view.findViewById(R.id.ll_view);
            tv_category = (TextView) view.findViewById(R.id.tv_category);
            tv_title = (TextView) view.findViewById(R.id.tv_title);
            tv_date = (TextView) view.findViewById(R.id.tv_dateAndtime);
            tv_like = (TextView) view.findViewById(R.id.tv_total_like);
            tv_comment = (TextView) view.findViewById(R.id.tv_total_comment);
            b_like = (Button) view.findViewById(R.id.b_like);
            b_comment = (Button) view.findViewById(R.id.b_comment);


        }



          /*  if (sessionManager.isLoggedIn())
            {
                Intent intent=new Intent(context,MainActivity123.class);
                intent.putExtra("productCode",android.get(getPosition()).getAndroid_product_code());
                intent.putExtra("name",android.get(getPosition()).getAndroid_product_name());
                //intent.putExtra("o_price",android.get(getPosition()).getAndroid_o_price());
                //intent.putExtra("d_price",android.get(getPosition()).getAndroid_d_price());
                intent.putExtra("desp",android.get(getPosition()).getAndroid_product_desp());
                //intent.putExtra("req",android.get(getPosition()).getAndroid_product_req());
                context.startActivity(intent);

            }
            else
            {
                Toast.makeText(context,"First Sign In",Toast.LENGTH_LONG).show();
            }

*/
        }
        }


