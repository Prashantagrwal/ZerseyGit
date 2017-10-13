package com.example.dell.zersey.PHPServer;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;


public class UserPHP extends AsyncTask<String,Void,String>
{
    Context context;
    String JsonString;
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_ID = "id";
    private static final String TAG_MESSAGE = "message";
    public UserPHP(Context context) {
        this.context = context;
    }

    String result;
    @Override
    protected String doInBackground(String... params) {
        String google_id=params[0],name=params[1],email=params[2];
        String LINK_EMAIL="email",LINK_NAME="name",LINK_GOOGLE="google_id";
        Uri builturi = Uri.parse("https://onlinefir.000webhostapp.com/Zersey/LoginData.php?").buildUpon().
                appendQueryParameter(LINK_GOOGLE,google_id).
                appendQueryParameter(LINK_NAME,name).
                appendQueryParameter(LINK_EMAIL,email)
                .build();
        HttpFetch httpFetch=new HttpFetch();
        Log.e("value",builturi.toString());
        result= httpFetch.httpResquest(builturi.toString(),"check");
        return result;
    }

    @Override
    protected void onPostExecute(String s) {
        JsonString=s;
        super.onPostExecute(s);
        showid();
    }

    private void showid() {
        try {

            JSONObject jsonObj = new JSONObject(JsonString);
            int success = jsonObj.getInt(TAG_SUCCESS);

            if (success == 1) {
                Toast.makeText(context,"Loged in successfully",Toast.LENGTH_LONG).show();
            } else if (success == 0)
            {
                String message=jsonObj.getString(TAG_MESSAGE);
                Toast.makeText(context,message,Toast.LENGTH_LONG).show();
            }
        }catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

