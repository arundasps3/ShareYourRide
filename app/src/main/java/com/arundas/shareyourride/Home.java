package com.arundas.shareyourride;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Home extends AppCompatActivity {



    Button SignIn,SignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        SignIn = findViewById(R.id.signinbutton);
        SignUp = findViewById(R.id.signupbutton);

        SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Home.this,SignIn.class);
                startActivity(i);

            }
        });


         SignUp.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent j = new Intent(Home.this,SignUp.class);
            startActivity(j);
                }
          });


    }

}
