package com.example.dell.zersey.PHPServer;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import com.example.dell.zersey.Session.SessionManagar;


public class EventUpload extends AsyncTask<String,String,String>
        {

            Context c; String check;
            public EventUpload(Context c)
            {
                this.c=c;
            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected String doInBackground(String... params) {
                 check=params[0];


               Uri builturi=null;
                SessionManagar sessionManager=new SessionManagar(c);
                String userid =sessionManager.getId();
                String LINK_CATEGORY="category",LINK_TITLE="title",LINK_DESP="description",
                        LINK_DATE="event_date",LINK_TIME="event_time", LINK_IMAGE="image_url",
                        LINK_CHECK="check",LINK_VIDEO="video_url",LINK_ID="google_id",LINK_EVENT_ID="event_id";

                if(check.equals("1"))
                {
                    String category=params[1],title=params[2],desp=params[3],event_date=params[4],
                            event_time=params[5],image_url="empty",video_url="empty",event_id=params[6];

                     builturi = Uri.parse("https://onlinefir.000webhostapp.com/Zersey/event_detail.php?").buildUpon().
                            appendQueryParameter(LINK_CHECK,check)
                             .appendQueryParameter(LINK_ID,userid)
                             .appendQueryParameter(LINK_CATEGORY,category)
                            .appendQueryParameter(LINK_TITLE,title)
                            .appendQueryParameter(LINK_DESP,desp)
                             .appendQueryParameter(LINK_DATE,event_date)
                             .appendQueryParameter(LINK_TIME,event_time)
                             .appendQueryParameter(LINK_IMAGE,image_url)
                             .appendQueryParameter(LINK_VIDEO,video_url)
                             .appendQueryParameter(LINK_EVENT_ID,event_id)
                             .build();
                    Log.e("url",builturi.toString());

                }
                else if(check.equals("4")){
                    String image_url=params[1],event_id=params[2];
                    builturi = Uri.parse("https://onlinefir.000webhostapp.com/Zersey/event_detail.php?").buildUpon().
                            appendQueryParameter(LINK_CHECK,check)
                            .appendQueryParameter(LINK_IMAGE,image_url)
                            .appendQueryParameter(LINK_EVENT_ID,event_id)
                            .build();



                }
                else if(check.equals("5")){
                    String video_url=params[1],event_id=params[2];
                    builturi = Uri.parse("https://onlinefir.000webhostapp.com/Zersey/event_detail.php?").buildUpon().
                            appendQueryParameter(LINK_CHECK,check)
                            .appendQueryParameter(LINK_VIDEO,video_url)
                            .appendQueryParameter(LINK_EVENT_ID,event_id)
                            .build();

                }

                assert builturi != null;
                return new HttpFetch().httpResquest(builturi.toString(), "add");
            }

            @Override
            protected void onPostExecute(String s)
            {
                super.onPostExecute(s);
            }
        }
