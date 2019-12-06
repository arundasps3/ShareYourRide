package com.arundas.shareyourride;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class User extends AppCompatActivity {



    Button driver,passenger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        driver = findViewById(R.id.driver);
        passenger = findViewById(R.id.passenger);


        driver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(User.this,PostRide.class);
                startActivity(i);
            }
        });


        passenger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(User.this,SearchRide.class);
                startActivity(i);

            }
        });

    }
}
