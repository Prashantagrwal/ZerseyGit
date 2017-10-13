package com.example.dell.zersey.Event;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.example.dell.zersey.R;


public class YourEvent extends AppCompatActivity {

  FragmentMain fragmentMain;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.your_event);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        fragmentMain =new FragmentMain(3);
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_1,fragmentMain,"Main").commit();
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
