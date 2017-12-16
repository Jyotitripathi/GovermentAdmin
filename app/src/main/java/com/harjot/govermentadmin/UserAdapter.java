package com.harjot.govermentadmin;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Harjot on 11/29/2017.
 */


public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    private List<User> userlist;
  //  User user = new User();

    Context context;
    int resource;
    ArrayList<User> list;

    public UserAdapter(Context context, int resource, ArrayList<User> objects) {

        this.context = context;
        this.resource = resource;
        list = objects;
    }


    @Override
    public UserAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(resource, parent, false);
        ViewHolder holder=new ViewHolder(itemView);
        return holder;
    }


    @Override
    public void onBindViewHolder(UserAdapter.ViewHolder holder, int position) {
        User user = list.get(position);
        holder.name.setText("Name  - " + user.getName());
        holder.email.setText("Email - " + user.getEmail());
        holder.phone.setText("Phone - " + user.getPhoneno());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder
    {
        //TextView txtName;
        //TextView txtEmail;

        TextView name, email, phone;

        ViewHolder(View view)
        {
                super(view);
                name = (TextView) view.findViewById(R.id.txtname);
                email = (TextView) view.findViewById(R.id.txtemail);
                phone = (TextView) view.findViewById(R.id.txtphone);
        }
        }

    }
