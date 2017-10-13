package com.example.dell.zersey.Event;


import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.dell.zersey.PHPServer.EventUpload;
import com.example.dell.zersey.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.UUID;

public class NewEvent extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {
Button imag,video,upload;
    Spinner spinner;
    ArrayAdapter<CharSequence> adapter;
    int SELECT_PICTURE = 100,SELECT_VIDEO=101;
  String path="";
ImageView image_view;
    VideoView video_view;
    EditText et_date,et_time,et_title,et_desp;
    final Calendar  myCalendar = Calendar.getInstance();
   String log_tag=NewEvent.class.getSimpleName();
    Bitmap myBitmap;
    String pos_string,video_path="";
    String[] event_category;
    byte[] image_byte_data,bytes_video;
    File imgFile=null;
    File file_video=null;
    FirebaseStorage firebaseStorage= FirebaseStorage.getInstance();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_event);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        spinner= (Spinner) findViewById(R.id.spinner);
        select_category();
        image_view=(ImageView) findViewById(R.id.image_view);
        video_view=(VideoView) findViewById(R.id.video_view);
        imag=(Button) findViewById(R.id.b_imag);
        video=(Button) findViewById(R.id.b_video);
        upload=(Button) findViewById(R.id.b_upload);
        event_category = getResources().getStringArray(R.array.category_array);
        imag.setOnClickListener(this);
        video.setOnClickListener(this);
        upload.setOnClickListener(this);



        et_date= (EditText) findViewById(R.id.editText_date);
        et_time=(EditText) findViewById(R.id.editText_time);
        et_title=(EditText) findViewById(R.id.editText_title);
        et_desp=(EditText) findViewById(R.id.editText_desp);
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };



        et_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(NewEvent.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();

            }
        });

        et_time.setOnClickListener(this);

       }

    private void updateLabel() {
        String myFormat = "dd/MM/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.UK);
        et_date.setText(sdf.format(myCalendar.getTime()));
//    et_date.setEnabled(false);
    }

    private void select_category() {
        spinner.setOnItemSelectedListener(this);
        adapter=ArrayAdapter.createFromResource(NewEvent.this,R.array.category_array,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }


    public String getPath(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == SELECT_PICTURE) {
            if (resultCode == RESULT_OK) {
                Uri selectedMediaUri = data.getData();
                if (selectedMediaUri.toString().contains("image")) {
                    //handle image

                    Uri fileUri = data.getData();
                    path = getPath(fileUri);
                    Log.e(log_tag, path);

                    imgFile = new File(path);
                    if (imgFile.exists()) {
                        myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                        ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
                        myBitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArray);
                        image_byte_data = byteArray.toByteArray();
                    }
                    image_view.setVisibility(View.VISIBLE);
                    image_view.setImageBitmap(myBitmap);

                }
            }
        } else if (requestCode == SELECT_VIDEO) {
            Log.e(log_tag, "inside select video");
            if (resultCode == RESULT_OK) {
                Log.e(log_tag, "inside result video");
                Uri selectedMediaUri = data.getData();

                if (selectedMediaUri.toString().contains("video")) {
                    Log.e(log_tag, "inside final");
                    //handle video
                    Toast.makeText(getApplicationContext(),
                            "User  Video capture", Toast.LENGTH_LONG)
                            .show();
                    Uri video_uri = data.getData();
                    video_path = getPath(video_uri);
                    Log.e(log_tag, video_path);

                    MediaController media = new MediaController(NewEvent.this);

                    media.setAnchorView(video_view);
                    video_view.setVisibility(View.VISIBLE);
                    video_view.setMediaController(media);
                    video_view.setVideoURI(video_uri);

                    file_video = new File(video_path);


                    FileInputStream fis = null;
                    try {
                        fis = new FileInputStream(video_path);

                        ByteArrayOutputStream bos = new ByteArrayOutputStream();
                        byte[] b = new byte[1024];

                        for (int readNum; (readNum = fis.read(b)) != -1; ) {
                            bos.write(b, 0, readNum);
                        }

                        bytes_video = bos.toByteArray();

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                }

            } else if (resultCode == RESULT_CANCELED) {

                // user cancelled Image capture
                Toast.makeText(getApplicationContext(),
                        "User cancelled image capture", Toast.LENGTH_SHORT)
                        .show();

            } else {
                // failed to capture image
                Toast.makeText(getApplicationContext(),
                        "Sorry! Failed to capture image", Toast.LENGTH_SHORT)
                        .show();
            }
        }
    }



    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
        if(position==0)
        {
        pos_string=event_category[position];
        }
        else if(position==1)
        {
            pos_string=event_category[position];
          //  PostionTwo();
        }
        else if(position==2)
        {
            pos_string=event_category[position];
          //  PostionThree();
        }else if(position==3)
        {
            //  PostionThree();
            pos_string=event_category[position];
        }else if(position==4)
        {
            //  PostionThree();
            pos_string=event_category[position];
        }else if(position==5)
        {
            pos_string=event_category[position];
            //  PostionThree();
        }else if(position==6)
        {
            //  PostionThree();
            pos_string=event_category[position];
        }else if(position==7)
        {
            pos_string=event_category[position];
            //  PostionThree();
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onClick(View view) {
           int id=view.getId();
        if(id==R.id.b_imag){
            Intent pickIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            // pickIntent.setType("image/* video/*");
            pickIntent.setType("image/*");
            startActivityForResult(pickIntent, SELECT_PICTURE);
        }
        else if(id==R.id.b_video){
            Intent pickIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Video.Media.EXTERNAL_CONTENT_URI);
            // pickIntent.setType("image/* video/*");
            pickIntent.setType("video/*");
            startActivityForResult(pickIntent, SELECT_VIDEO);
        }
        else if(id==R.id.b_upload){

            String title_str=et_title.getText().toString();
            String time_str=et_time.getText().toString();
            String date_str=et_date.getText().toString();
            String desp_str=et_desp.getText().toString();

            if(pos_string.equals("Choose Event Type")){
                Toast.makeText(NewEvent.this, "Choose a valid event", Toast.LENGTH_LONG).show();
            }

            if(time_str.matches("") || date_str.matches("")
                    || title_str.matches("") || desp_str.matches("")){
                Toast.makeText(NewEvent.this, "Some feild is empty", Toast.LENGTH_LONG).show();
            }
            String value=null;
             if(path.equals("") && video_path.equals("")){
                Toast.makeText(NewEvent.this, "Choose a image or video", Toast.LENGTH_LONG).show();
            }

            if(!pos_string.equals("Choose Event Type") &&
                    !et_time.getText().toString().matches("") && !et_date.getText().toString().matches("")
                    && !et_title.getText().toString().matches("") && !et_desp.getText().toString().matches("")
                    && (!path.equals("") || !video_path.equals(""))){

                final String random=UUID.randomUUID().toString();

        new EventUpload(NewEvent.this).execute("1",pos_string,title_str,desp_str,date_str,time_str
        ,random);

                Toast.makeText(NewEvent.this, "Everything is fine", Toast.LENGTH_LONG).show();

               final ProgressDialog loading = ProgressDialog.show(NewEvent.this, "", "loading...", false, false);



                if(!path.equals("")){


                    String firebase_path="Images/"+ random +".png";
                    StorageReference storage=firebaseStorage.getReference(firebase_path);
                    UploadTask uploadTask=storage.putBytes(image_byte_data);
                    uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Toast.makeText(NewEvent.this,"Image Uploaded Successfully",Toast.LENGTH_SHORT).show();


                            Uri download_image_url=taskSnapshot.getDownloadUrl();
                            new EventUpload(NewEvent.this).execute("4",download_image_url.toString(),random);

                    if(video_path.equals("")){
                        loading.dismiss();
//                        callServer.callServer();
                        finish();
                    }
                        }
                    });


                }

                if(!video_path.equals("")){
                    String random1=UUID.randomUUID().toString();

                    String firebase_path="Videos/"+ random1 +".mp3";
                    StorageReference storage=firebaseStorage.getReference(firebase_path);
                    UploadTask uploadTask=storage.putBytes(bytes_video);
                    uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Uri download_video_url=taskSnapshot.getDownloadUrl();
                            new EventUpload(NewEvent.this).execute("5",download_video_url.toString(),random);
                            loading.dismiss();
  //                          callServer.callServer();
                            finish();
                        }
                    });
                }
            }
        }
        else if(id==R.id.editText_time){


            Calendar mcurrentTime = Calendar.getInstance();
            int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
            int minute = mcurrentTime.get(Calendar.MINUTE);
            TimePickerDialog mTimePicker;
            mTimePicker = new TimePickerDialog(NewEvent.this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {

                  if(selectedHour >12)
                  {
                      selectedHour=selectedHour-12;

                      if (selectedMinute<10){
                          et_time.setText( selectedHour + ":0" + selectedMinute+" PM");
                      }else
                      et_time.setText( selectedHour + ":" + selectedMinute+" PM");
                  }
                  else{
                     if(selectedMinute<10){
                         et_time.setText( selectedHour + ":0" + selectedMinute+" AM");
                     }else
                      et_time.setText( selectedHour + ":" + selectedMinute+" AM");
                  }

                }
            }, hour, minute, true);//Yes 24 hour time
            mTimePicker.setTitle("Select Time");
            mTimePicker.show();

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
