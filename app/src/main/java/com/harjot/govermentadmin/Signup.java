package com.harjot.govermentadmin;

import android.content.Context;
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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Signup extends AppCompatActivity {
    Register register=new Register();
    String Email,Password,aadhar,Name,Number;
    Button sign;
    EditText name;
    EditText Email1;
    EditText Password1;
    EditText Aadhar;
    EditText number;
    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;
    String MobilePattern= "[0-9]{10}";
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        getSupportActionBar().setTitle("Welcome Register For Admin");
        name=(EditText)findViewById(R.id.Name);
        Email1=(EditText)findViewById(R.id.Email);
        Password1=(EditText)findViewById(R.id.Password);
        Aadhar=(EditText)findViewById(R.id.Aadhar);
        number=(EditText)findViewById(R.id.Number);
        sign=(Button)findViewById(R.id.login);
        checkForEmail();
        checkForMobile();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        firebaseAuth = FirebaseAuth.getInstance();


        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 Email=Email1.getText().toString().trim();
                Password=Password1.getText().toString().trim();
                aadhar=Aadhar.getText().toString().trim();
                Number=number.getText().toString().trim();
                Name=name.getText().toString().trim();
                register.setName(Name);
                register.setEmail(Email);
                register.setAadharcard(aadhar);
                register.setPassword(Password);
                register.setPhone(Number);
                Toast.makeText(Signup.this, "Name"+register.getName()+register.getAadharcard()+register.getPassword()+register.getPhone(), Toast.LENGTH_SHORT).show();

                if (!Email1.getText().toString().trim().equals("") && !Password1.getText().toString().trim().equals("") &&!Aadhar.getText().toString().trim().equals("") && !number.getText().toString().trim().equals("") && !name.getText().toString().trim().equals("")){
                    Register();
                }
                else{

                    AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(Signup.this);

                    dlgAlert.setMessage("Please Fill All Detail........");
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


    }
    void Register() {
        firebaseAuth.createUserWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful())
                {
                    Toast.makeText(Signup.this, "Registered Successful", Toast.LENGTH_SHORT).show();
                  //  Registeruser(task.getResult().getUser());
                   // Toast.makeText(Signup.this, "user "+task.getResult().getUser(), Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(Signup.this,Sigin.class);
                    startActivity(i);


                }
                else{
                    Toast.makeText(Signup.this, "Ex "+task.getException(), Toast.LENGTH_SHORT).show();
                    Toast.makeText(Signup.this, "Please Regestration Again", Toast.LENGTH_SHORT).show();
                    Toast.makeText(Signup.this, "register "+task.getResult().getUser(), Toast.LENGTH_SHORT).show();

                }

            }
        });
    }
    void Registeruser(FirebaseUser user){

        databaseReference.child("Admin").child(user.getUid()).setValue(register);
    }


    public boolean checkForEmail() {
        Context c;
        EditText mEtEmail=(EditText)findViewById(R.id.Email);
        String mStrEmail = mEtEmail.getText().toString();
        if (android.util.Patterns.EMAIL_ADDRESS.matcher(mStrEmail).matches()) {
            return true;
        }
        Toast.makeText(this,"Email is not valid", Toast.LENGTH_LONG).show();
        return false;
    }


    public boolean checkForMobile() {
        Context c;
        EditText mEtMobile=(EditText)findViewById(R.id.Number);
        String mStrMobile = mEtMobile.getText().toString();
        if (android.util.Patterns.PHONE.matcher(mStrMobile).matches()) {
            return true;
        }
        Toast.makeText(this,"Phone No is not valid", Toast.LENGTH_LONG).show();
        return false;
    }



}
