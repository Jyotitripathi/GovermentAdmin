package com.harjot.govermentadmin;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button btn;
    DatabaseReference database = FirebaseDatabase.getInstance().getReference("One");
    DatabaseReference ref;

    ArrayList<User> userList;
    RecyclerView recyclerView;
    UserAdapter uAdapter;
    int count=0;
    String uid;
FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userList= new ArrayList<User>();
        recyclerView = (RecyclerView) findViewById(R.id.rec);
        ref = FirebaseDatabase.getInstance().getReference("one");
user= FirebaseAuth.getInstance().getCurrentUser();

        uAdapter=new UserAdapter(MainActivity.this,R.layout.list_row, userList);

        //Toast.makeText(this, "l "+ userList, Toast.LENGTH_SHORT).show();
        database.addChildEventListener(new ChildEventListener()
        {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s)
            {

                User u1 = new User(dataSnapshot.getKey().toString(),dataSnapshot.child("name").getValue().toString(), dataSnapshot.child("email").getValue().toString(), dataSnapshot.child("mobile").getValue().toString() );
                //Toast.makeText(MainActivity.this, "Data snapshot"+dataSnapshot.getKey(), Toast.LENGTH_SHORT).show();
                userList.add(u1);
                uAdapter.notifyDataSetChanged();


            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this,recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {


                uid= userList.get(position).getId();
                AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(MainActivity.this);

                dlgAlert.setMessage("Are you Sure To Delete User");
                dlgAlert.setTitle("Delete User");

                dlgAlert.setPositiveButton("Yes", null);
dlgAlert.setPositiveButton("yes", new DialogInterface.OnClickListener() {
    @Override
    public void onClick(DialogInterface dialogInterface, int i) {
        fireuser(uid);
    }
});
                dlgAlert.setNegativeButton("NO",null);
                dlgAlert.setCancelable(true);
                dlgAlert.create().show();

                dlgAlert.setPositiveButton("Ok",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));


        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        //recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(uAdapter);



    }
public void fireuser(String id)
{

database.child(id).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task)
                {
                    if (task.isSuccessful())
                    {
                        Toast.makeText(MainActivity.this, "User account deleted", Toast.LENGTH_SHORT).show();

                    }

                    else
                    {
                        //Toast.makeText(MainActivity.this, "error " + task.getException(), Toast.LENGTH_LONG).show();
                        Toast.makeText(MainActivity.this, "User account Not deleted", Toast.LENGTH_SHORT).show();
                    }
                }
            });


}


 /*   @Override
    protected void onStart() {
        super.onStart();
       ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {

                for (DataSnapshot oneSnapshot :dataSnapshot.getChildren())
                {
                    Toast.makeText(MainActivity.this, "Data " + dataSnapshot, Toast.LENGTH_SHORT).show();
                    Log.i("DATA", dataSnapshot.toString());

                    Toast.makeText(MainActivity.this, "datasnaps"+dataSnapshot, Toast.LENGTH_SHORT).show();
User user=oneSnapshot.getValue(User.class);
                    userList.add(user);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/
    //}
}
