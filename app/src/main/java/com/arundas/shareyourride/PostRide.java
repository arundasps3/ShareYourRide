package com.arundas.shareyourride;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.arundas.RideModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class PostRide extends AppCompatActivity {

    EditText Departure,Destination,Date,Time,MessageEntered,Fare;
    ImageView date_iv,time_iv;
    Button post;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    TimePickerDialog timePickerDialog;
    Calendar calendar;
    int currentHour;
    int currentMinute;
    String amPm;

    private DatePickerDialog.OnDateSetListener mDateSetListener;

    List<RideModel> rideDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_ride);

        Departure = findViewById(R.id.depar);
        Destination = findViewById(R.id.dest);
        Date = findViewById(R.id.date);
        Time = findViewById(R.id.time);
        MessageEntered = findViewById(R.id.messageEntered);
        Fare =findViewById(R.id.fare);
        date_iv =findViewById(R.id.iv_date);
        time_iv= findViewById(R.id.iv_time);
        post = findViewById(R.id.postbutton);


        databaseReference = firebaseDatabase.getInstance().getReference("Posted_Rides");

        rideDetails = new ArrayList<>();

        date_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        PostRide.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();


            }

        });

      mDateSetListener = new DatePickerDialog.OnDateSetListener() {
          @Override
          public void onDateSet(DatePicker view, int year, int month, int day) {


              month = month + 1;
             // Log.d(P, "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);

              String date = month + "/" + day + "/" + year;
              Date.setText(date);
          }
      };


        time_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                calendar = Calendar.getInstance();
                currentHour = calendar.get(Calendar.HOUR_OF_DAY);
                currentMinute = calendar.get(Calendar.MINUTE);

                timePickerDialog = new TimePickerDialog(PostRide.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                        if (hourOfDay >= 12) {
                            amPm = "PM";
                        } else {
                            amPm = "AM";
                        }
                        Time.setText(String.format("%02d:%02d", hourOfDay, minutes) + amPm);
                    }
                }, currentHour, currentMinute, false);

                timePickerDialog.show();
            }
        });



     post.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {

         String Ride_Departure = Departure.getText().toString();
         String Ride_Destination  = Destination.getText().toString();
         String Ride_Date = Date.getText().toString();
         String Ride_time = Time.getText().toString();
         String Ride_Message = MessageEntered.getText().toString();
         String Ride_fare = Fare.getText().toString();

             if (TextUtils.isEmpty(Ride_Departure)) {
                 Toast.makeText(PostRide.this, "please enter Departure", Toast.LENGTH_LONG).show();
                 return;
             }

             if (TextUtils.isEmpty(Ride_Destination)) {
                 Toast.makeText(PostRide.this, "please enter Destination", Toast.LENGTH_LONG).show();
                 return;
             }


             if (TextUtils.isEmpty(Ride_Date)) {
                 Toast.makeText(PostRide.this, "please select the date", Toast.LENGTH_LONG).show();
                 return;
             }

             if (TextUtils.isEmpty(Ride_time)) {
                 Toast.makeText(PostRide.this, "please select the time", Toast.LENGTH_LONG).show();
                 return;
             }


             if (TextUtils.isEmpty(Ride_Message)) {
                 Toast.makeText(PostRide.this, "please enter message", Toast.LENGTH_LONG).show();
                 return;
             }
             if (TextUtils.isEmpty(Ride_fare)) {
                 Toast.makeText(PostRide.this, "please enter Fare", Toast.LENGTH_LONG).show();
                 return;
             }
             else {


                 String id = databaseReference.push().getKey();
                 Log.d("Key", id);
                 RideModel rideinfo = new RideModel(Ride_Departure, Ride_Destination, Ride_Date, Ride_time, Ride_Message, Ride_fare);

                 databaseReference.child(id).setValue(rideinfo);

                 Toast.makeText(PostRide.this, "Ride Posted", Toast.LENGTH_SHORT).show();
             }

//             FirebaseDatabase.getInstance().getReference("Posted_Rides")
//                     .child(id).setValue(rideinfo).addOnCompleteListener(new OnCompleteListener<Void>() {
//                 @Override
//                 public void onComplete(@NonNull Task<Void> task) {
//
//
//                     Toast.makeText(PostRide.this, "Ride Posted", Toast.LENGTH_SHORT).show();
//                    Departure.setText("");
//                    Destination.setText("");
//                    Date.setText("");
//                    Time.setText("");
//                    MessageEntered.setText("");
//                    Fare.setText("");
//
//
//                 }
//             });
         }
     });

    }

}
