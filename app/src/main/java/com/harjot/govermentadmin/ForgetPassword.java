package com.harjot.govermentadmin;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import at.markushi.ui.CircleButton;

public class ForgetPassword extends AppCompatActivity implements View.OnClickListener {
    EditText email;
    String em;
    ProgressDialog pd;
    FirebaseAuth firebaseAuth;
    CircleButton c1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        firebaseAuth = FirebaseAuth.getInstance();
        pd = new ProgressDialog(this);
        email = (EditText) findViewById(R.id.email);
        c1 = (CircleButton) findViewById(R.id.L2);
        c1.setOnClickListener(this);
        pd.setMessage("Please Wait......");

    }

    @Override
    public void onClick(View view) {
        if (!email.getText().toString().trim().isEmpty()) {
            em = email.getText().toString().trim();

            pd.show();
            firebaseAuth.sendPasswordResetEmail(em).addOnCompleteListener(new OnCompleteListener<Void>() {

                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    pd.dismiss();
                    if (task.isSuccessful()) {
                        Toast.makeText(ForgetPassword.this, "We Have sent you instructions to reset your password !", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(ForgetPassword.this, "Failed to send password reset email !", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        } else {
            Toast.makeText(this, "Enter Your Registered Email Id", Toast.LENGTH_SHORT).show();
        }

    }
    }

