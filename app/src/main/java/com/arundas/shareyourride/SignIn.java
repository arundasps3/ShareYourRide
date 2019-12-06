package com.arundas.shareyourride;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignIn extends AppCompatActivity {

    private FirebaseAuth mAuth;
    EditText email,password;
    Button  SignIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        mAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.email);
        password =findViewById(R.id.password);

        SignIn = findViewById(R.id.signin_button);

        SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String EmailEntered = email.getText().toString();
                String PasswordEntered = password.getText().toString();


            mAuth.signInWithEmailAndPassword(EmailEntered,PasswordEntered)
                    .addOnCompleteListener(SignIn.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {


                            if (task.isSuccessful())
                            {
                                Toast.makeText(SignIn.this,"Login is Successful",Toast.LENGTH_LONG).show();
                                Intent i= new Intent(getApplicationContext(),User.class);
                                startActivity(i);

                            }

                            else
                                {

                                    Toast.makeText(SignIn.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                                }
                        }
                    });



            }
        });
    }
}
