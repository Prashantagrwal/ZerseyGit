package com.example.dell.zersey;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.dell.zersey.Session.LoginWithGoogle;
import com.example.dell.zersey.Session.SessionManagar;


public class SplashScreen extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        int SPLASH_TIME_OUT = 3000;
        new Handler().postDelayed(new Runnable() {


            @Override
            public void run() {

                SessionManagar sessionManagar=new SessionManagar(getApplicationContext());

                if(sessionManagar.isLoggedIn()){
                Intent i = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(i);
                finish();
                }
                else{
                    Intent login=new Intent(SplashScreen.this, LoginWithGoogle.class);
                    startActivity(login);
                    finish();
                }
            }
        }, SPLASH_TIME_OUT);



    }


}
