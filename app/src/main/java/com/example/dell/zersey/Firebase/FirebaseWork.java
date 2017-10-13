package com.example.dell.zersey.Firebase;


import com.example.dell.zersey.POJO.CommentPojo;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FirebaseWork {

    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;
    private static final String UPLOAD_EVENT="upload_event";
    private static final String EVENT_LIKE="Like";
    private static final String USER_LIKED="event_liked";
    private static final String EVENT_COMMENT="Comments";
    private static final String USER_COMMENT="user_comment";
    private static final String NAME="User_Name";
    private static final String USER_NAME="name";
    private static final String GOOGLE_USER="id";

    public FirebaseWork(){
    mFirebaseInstance=FirebaseDatabase.getInstance();
    mFirebaseDatabase=mFirebaseInstance.getReference();
}

public void like_event(String event_id,String google_id){

    String key=mFirebaseDatabase.push().getKey();
    mFirebaseDatabase.child(UPLOAD_EVENT).child(event_id).child(EVENT_LIKE).child(key).child(GOOGLE_USER).setValue(google_id);
}

public void comment_event(String event_id,String  name,String user_comments){
    final String key=mFirebaseDatabase.push().getKey();
    mFirebaseDatabase.child(UPLOAD_EVENT).child(event_id).child(EVENT_COMMENT).child(key).
            setValue(new CommentPojo(user_comments,name));
    // mFirebaseDatabase.child(UPLOAD_EVENT).child(event_id).child(EVENT_COMMENT).child(key).child(USER_NAME).setValue(name);
   /// mFirebaseDatabase.child(UPLOAD_EVENT).child(event_id).child(EVENT_COMMENT).child(key).child(USER_COMMENT).setValue(user_comments);
}



    public void isLiked(String event_id,String google_id,ValueEventListener valueEvent) {

        mFirebaseDatabase.child(UPLOAD_EVENT).child(event_id).child(EVENT_LIKE).
                orderByChild(GOOGLE_USER).equalTo(google_id).addListenerForSingleValueEvent(valueEvent);

    }

    public  void TotalLiked(String event_id,ValueEventListener valueEventListener)
    {
        mFirebaseDatabase.child(UPLOAD_EVENT).child(event_id).child(EVENT_LIKE).addValueEventListener(valueEventListener);
    }

    public  void TotalComment(String event_id,ValueEventListener valueEventListener)
    {
        mFirebaseDatabase.child(UPLOAD_EVENT).child(event_id).child(EVENT_COMMENT).addValueEventListener(valueEventListener);
    }
}

