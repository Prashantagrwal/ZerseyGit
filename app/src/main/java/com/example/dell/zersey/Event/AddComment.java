package com.example.dell.zersey.Event;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.zersey.Adapter.CommentDetailAdapter;
import com.example.dell.zersey.Firebase.FirebaseWork;
import com.example.dell.zersey.POJO.CommentPojo;
import com.example.dell.zersey.R;
import com.example.dell.zersey.Session.SessionManagar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by DELL on 13/10/2017.
 */

public class AddComment extends AppCompatActivity {

    TextView tv_category, tv_title, tv_date, tv_desp;
    Button send;
    EditText et_comment;
    Intent intent;
    FirebaseWork firebaseWork;
    String event_id,user_name;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_comment);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tv_category = (TextView) findViewById(R.id.tv_category1);
        tv_title = (TextView) findViewById(R.id.tv_title1);
        tv_date = (TextView) findViewById(R.id.tv_dateAndtime1);
        tv_desp = (TextView) findViewById(R.id.tv_desp1);
        send= (Button) findViewById(R.id.b_send);
        et_comment= (EditText) findViewById(R.id.et_comment);
        intent=getIntent();

          event_id="";
          firebaseWork=new FirebaseWork();
        if(intent!=null){
            event_id=intent.getStringExtra("event_id");
            tv_category.setText(intent.getStringExtra("category"));
            tv_title.setText(intent.getStringExtra("title"));
            tv_date.setText(intent.getStringExtra("date"));
            tv_desp.setText(intent.getStringExtra("desp"));
        }
        final ArrayList<CommentPojo> commentPojos=new ArrayList<CommentPojo>();
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String comment_str=et_comment.getText().toString();

                if(comment_str.equals("")){
                    Toast.makeText(AddComment.this,"empty message cannot be send",Toast.LENGTH_LONG).show();
                }
                else{
                    commentPojos.clear();
                    Toast.makeText(AddComment.this,"Your comment has been post",Toast.LENGTH_LONG).show();
                    HashMap<String,String> hashMap=new SessionManagar(AddComment.this).getUserDetails();
                    firebaseWork.comment_event(event_id,hashMap.get(SessionManagar.KEY_NAME),comment_str);
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                     et_comment.setText("");
                }
            }
        });

        firebaseWork.TotalComment(event_id, new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists()){
                    for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                        CommentPojo commentPojo=new CommentPojo();
                        commentPojo.setUser_name(dataSnapshot1.child("user_name").getValue().toString());
                        commentPojo.setUser_comment(dataSnapshot1.child("user_comment").getValue().toString());
                        commentPojos.add(commentPojo);
                    }
                    RecyclerView recyclerView = (RecyclerView) findViewById(R.id.card_recycler_view);
                    assert recyclerView != null;
                    recyclerView.setHasFixedSize(true);
                    RecyclerView.LayoutManager layoutManager = new GridLayoutManager(AddComment.this,1);
                    recyclerView.setLayoutManager(layoutManager);
                    CommentDetailAdapter adapter = new CommentDetailAdapter(AddComment.this,commentPojos);
                    recyclerView.setAdapter(adapter);
                }
                else{
                    Toast.makeText(AddComment.this,"no one has commented yet!!!",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });




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
