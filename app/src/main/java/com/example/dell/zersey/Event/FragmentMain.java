package com.example.dell.zersey.Event;

import android.app.ProgressDialog;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dell.zersey.Adapter.EventDetailAdapter;
import com.example.dell.zersey.PHPServer.HttpFetch;
import com.example.dell.zersey.POJO.EventDetailClass;
import com.example.dell.zersey.R;
import com.example.dell.zersey.Session.SessionManagar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class FragmentMain extends Fragment {

    String myJson;
    private JSONArray data= null;
    int success;
    private static String TAG_SUCCESS="success";
    private static String TAG_CATEGORY="category";
    private static String TAG_EVENT="event";
    private static String TAG_TITLE="title";
    private static String TAG_DESP="description";
    private static String TAG_EVENT_DATE="event_date";
    private static String TAG_EVENT_TIME="event_time";
    private static String TAG_IMAGE_URL="image_url";
    private static String TAG_VIDEO_URL="video_url";
    private static String TAG_EVENT_ID="event_id";
    int check_int;

    public FragmentMain(int i) {
        check_int=i;
    }
    String id;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view=inflater.inflate(R.layout.view_recycle,container,false);
       id= new SessionManagar(getActivity()).getId();

        getData(view);
        return view;
    }



        private void getData(final View view)
        {
            class Cart extends AsyncTask<String,Void,String > {

                ProgressDialog loading;
                @Override
                protected void onPreExecute() {
                    super.onPreExecute();

                   loading = ProgressDialog.show(getActivity(), "", "loading...", false, false);
                }

                @Override
                protected String doInBackground(String... params) {
                            String check_str=params[0];
                            String LINK_CHECK="check";

                    Uri builturi=null;
                  if(check_str.equals("2")){

                       builturi = Uri.parse("https://onlinefir.000webhostapp.com/Zersey/event_detail.php?").
                              buildUpon()
                              .appendQueryParameter(LINK_CHECK,check_str)
                              .build();

                  }
                  else if(check_str.equals("3")){
                      String google_id=params[1];
                      String LINK_ID="google_id";
                       builturi = Uri.parse("https://onlinefir.000webhostapp.com/Zersey/event_detail.php?").
                              buildUpon()
                              .appendQueryParameter(LINK_CHECK,check_str)
                              .appendQueryParameter(LINK_ID,google_id)
                              .build();

                  }
                    Log.e("url",builturi.toString());
                    return new HttpFetch().httpResquest(builturi.toString(), "fetch");
                }

                @Override
                protected void onPostExecute(String s) {
                    super.onPostExecute(s);

                    myJson = s;
                    showValues(view);
                    loading.dismiss();
                }
            }

            Cart c=new Cart();
            if(check_int==2)
                c.execute(String.valueOf(check_int));
            else if(check_int==3)
                c.execute(String.valueOf(String.valueOf(check_int)),id);
        }

    private void showValues(View view)
    {
        try {
            JSONObject jsonObj = new JSONObject(myJson);
            success = jsonObj.getInt(TAG_SUCCESS);
            if (success == 1) {

                data = jsonObj.getJSONArray(TAG_EVENT);
                int i;

                ArrayList<EventDetailClass> android_version = new ArrayList<>();

                for (i = 0; i < data.length(); i++)
                {
                    JSONObject value = data.getJSONObject(i);
                    EventDetailClass androidVersion = new EventDetailClass();
                    androidVersion.setCategory(value.getString(TAG_CATEGORY));
                    androidVersion.setTitle(value.getString(TAG_TITLE));
                    androidVersion.setDesp(value.getString(TAG_DESP));
                    androidVersion.setEvent_date(value.getString(TAG_EVENT_DATE));
                    androidVersion.setEvent_time(value.getString(TAG_EVENT_TIME));
                    androidVersion.setImage_url(value.getString(TAG_IMAGE_URL));
                    androidVersion.setVideo_url(value.getString(TAG_VIDEO_URL));
                    androidVersion.setEvent_id(value.getString(TAG_EVENT_ID));

                   Log.e("x",value.getString(TAG_CATEGORY));
                    Log.e("x",value.getString(TAG_TITLE));
                    Log.e("x",value.getString(TAG_DESP));
                    Log.e("x",value.getString(TAG_EVENT_DATE));
                    Log.e("x",value.getString(TAG_EVENT_TIME));
                    Log.e("x",value.getString(TAG_IMAGE_URL));
                    Log.e("x",value.getString(TAG_VIDEO_URL));



                    android_version.add(androidVersion);
                }
                RecyclerView recyclerView = (RecyclerView)
                        view.findViewById(R.id.card_recycler_view);
                assert recyclerView != null;
                recyclerView.setHasFixedSize(true);
                RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(),1);
                recyclerView.setLayoutManager(layoutManager);
                EventDetailAdapter adapter = new EventDetailAdapter(getActivity(),android_version,id);
                recyclerView.setAdapter(adapter);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}