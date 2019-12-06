package com.arundas.shareyourride;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {

    private FirebaseAuth mAuth;

    EditText name,email,password,confirmpassword,phone;
    Button signup,signin;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth = FirebaseAuth.getInstance();

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        confirmpassword = findViewById(R.id.confirmpassword);
        phone = findViewById(R.id.phone);

        signup =findViewById(R.id.sign_up);
        signin = findViewById(R.id.sign_in);

     databaseReference = firebaseDatabase.getInstance().getReference("UserDetails");



      signup.setOnClickListener(new View.OnClickListener() {




          @Override
          public void onClick(View v) {

              final String Name = name.getText().toString();
              final String Email = email.getText().toString();
              final String Password = password.getText().toString();
              String ConfirmPassword = confirmpassword.getText().toString();
              final String Phone = phone.getText().toString();


              if (TextUtils.isEmpty(Name)) {
                  Toast.makeText(SignUp.this, "please enter Name", Toast.LENGTH_LONG).show();
                  return;
              }

              if (TextUtils.isEmpty(Email)) {
                  Toast.makeText(SignUp.this, "please enter Email", Toast.LENGTH_LONG).show();
                  return;
              }


              if (TextUtils.isEmpty(Password)) {
                  Toast.makeText(SignUp.this, "please enter Password", Toast.LENGTH_LONG).show();
                  return;
              }

              if (TextUtils.isEmpty(ConfirmPassword)) {
                  Toast.makeText(SignUp.this, "please enter Re-enter Password", Toast.LENGTH_LONG).show();
                  return;
              }


              if (TextUtils.isEmpty(Phone)) {
                  Toast.makeText(SignUp.this, "please enter Re-enter Phone", Toast.LENGTH_LONG).show();
                  return;
              }


              if (!Password.equals(ConfirmPassword)) {
                  Toast.makeText(SignUp.this, "password doesnt match", Toast.LENGTH_LONG).show();
                  return;
              }


              mAuth.createUserWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                  @Override
                  public void onComplete(@NonNull Task<AuthResult> task) {

                      if (task.isSuccessful()) {

                          UserDetails userinfo = new UserDetails
                                  ( Name,Email,Phone);


                          FirebaseDatabase.getInstance().getReference("UserDetails")
                                  .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                  .setValue(userinfo).addOnCompleteListener(new OnCompleteListener<Void>() {
                              @Override
                              public void onComplete(@NonNull Task<Void> task) {


                                  Toast.makeText(SignUp.this, "Register Success", Toast.LENGTH_SHORT).show();
                                  startActivity(new Intent(getApplicationContext(),Home.class));


                              }
                          });




                      } else {


                          Toast.makeText(SignUp.this, "Register Failure,Please try again", Toast.LENGTH_SHORT).show();
                      }

                  }
              });
          }
      });





       signin.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {


               Intent i = new Intent(SignUp.this,SignIn.class);
               startActivity(i);
           }
       });

    }
}