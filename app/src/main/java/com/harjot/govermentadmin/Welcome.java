package com.harjot.govermentadmin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Welcome extends AppCompatActivity {
Button sigin,Signup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
sigin=(Button)findViewById(R.id.one);
        Signup=(Button)findViewById(R.id.two);
        sigin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Welcome.this,Sigin.class);
            startActivity(intent);

            }

        });
Signup.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {

        Intent intent1=new Intent(Welcome.this,Signup.class);
        startActivity(intent1);
    }
});
    }
}
