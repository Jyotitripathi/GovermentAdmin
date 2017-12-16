package com.harjot.govermentadmin;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Sigin extends AppCompatActivity {
EditText Email,Pass;
    String em,pa;
    Button Login;
    TextView f;
    String email,password;
    ProgressDialog progress;
Register register=new Register();
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sigin);
        f=(TextView)findViewById(R.id.forget);

        Email=(EditText)findViewById(R.id.email);
        if(Email.getText().toString().trim().equals("")){
            Email.setError("Please Enter Email");
        }
        Login=(Button)findViewById(R.id.submit);

        Pass=(EditText)findViewById(R.id.Password);
        if (Pass.getText().toString().trim().equals("")){
            Pass.setError("Please Enter Password");
        }
        firebaseAuth = FirebaseAuth.getInstance();

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email= Email.getText().toString().trim();

                password=Pass.getText().toString().trim();
                register.setEmail(email);
                register.setPassword(password);


                if (!Email.getText().toString().trim().equals("") && !Pass.getText().toString().trim().equals(""))
                {
                    login();
                }
                else
                {
                    AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(Sigin.this);

                    dlgAlert.setMessage("wrong password or username");
                    dlgAlert.setTitle("Error Message...");
                    dlgAlert.setPositiveButton("OK", null);
                    dlgAlert.setCancelable(true);
                    dlgAlert.create().show();

                    dlgAlert.setPositiveButton("Ok",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            });
                }


            }
        });

        f.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Sigin.this,ForgetPassword.class);
                startActivity(i);
            }
        });

         progress= new ProgressDialog(this);
        progress.setMessage("Authenticating User....... ");
        progress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progress.setIndeterminate(true);


    }


    void login(){

        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    progress.show();
                    Toast.makeText(Sigin.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(Sigin.this,Home.class);
                    startActivity(intent);

                }
                else{
                    Toast.makeText(Sigin.this, "Login Failure", Toast.LENGTH_SHORT).show();

                }

            }
        });
    }
}
