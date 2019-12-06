package com.arundas.shareyourride;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

public class SearchRide extends AppCompatActivity {

      AutoCompleteTextView Departure,Destination;
      Button search;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_ride);


        Departure = (AutoCompleteTextView)findViewById(R.id.departure);
        Destination = (AutoCompleteTextView) findViewById(R.id.destination);
        search = findViewById(R.id.search);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                searchingRide();
            }
        });


    }

    public void searchingRide() {

        Intent i = new Intent(SearchRide.this, searchList.class);
        startActivity(i);
    }
}
