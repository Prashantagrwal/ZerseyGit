package com.example.dell.zersey.Event;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.dell.zersey.R;
import com.squareup.picasso.Picasso;


public class Comment extends AppCompatActivity {

    TextView tv_category, tv_title, tv_date, tv_desp;
    ImageView imageView;
    VideoView video_view;
    Intent intent;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tv_category = (TextView) findViewById(R.id.tv_category2);
        tv_title = (TextView) findViewById(R.id.tv_title2);
        tv_date = (TextView) findViewById(R.id.tv_dateAndtime2);
        tv_desp = (TextView) findViewById(R.id.tv_desp2);
        imageView= (ImageView) findViewById(R.id.IV);
        video_view= (VideoView) findViewById(R.id.VI);

        intent=getIntent();
        if (intent!=null){
              tv_category.setText(intent.getStringExtra("category"));
              tv_title.setText(intent.getStringExtra("title"));
              tv_date.setText(intent.getStringExtra("date"));
              tv_desp.setText(intent.getStringExtra("desp"));
              String img_url=intent.getStringExtra("image_url");
              String video_url=intent.getStringExtra("video_url");

              if(!img_url.equals("empty")){
                  imageView.setVisibility(View.VISIBLE);
                  Picasso.with(Comment.this).load(img_url).placeholder(R.mipmap.e).into(imageView);
              }
              if(!video_url.equals("empty")){
                  MediaController media = new MediaController(Comment.this);

                  media.setAnchorView(video_view);
                  video_view.setVisibility(View.VISIBLE);
                  video_view.setMediaController(media);
                  video_view.setVideoURI(Uri.parse(video_url));
              }
          }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if(id==android.R.id.home)
            finish();
        //noinspection SimplifiableIfStatement

        return super.onOptionsItemSelected(item);
    }
}
