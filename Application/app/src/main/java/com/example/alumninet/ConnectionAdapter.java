package com.example.alumninet;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ConnectionAdapter extends ArrayAdapter<String> {
    Activity context;
    ArrayList<String> fullnameList = new ArrayList<>();
    ArrayList<String> usernameList = new ArrayList<>();
    ArrayList<String> emailList = new ArrayList<>();
//    ArrayList<String> interest1List = new ArrayList<>();
//    ArrayList<String> interest2List = new ArrayList<>();
//    ArrayList<String> interest3List = new ArrayList<>();
//    ArrayList<String> bioList = new ArrayList<>();


    public ConnectionAdapter(Activity context, ArrayList<String> fullnameList, ArrayList<String> usernameList,
                             ArrayList<String> emailList)/*, ArrayList<String> interest1List,
                             ArrayList<String> interest2List, ArrayList<String> interest3List,
                             ArrayList<String> bioList)*/ {
        super(context, R.layout.activity_connection_item, fullnameList);
        // TODO Auto-generated constructor stub

        this.context = context;
        this.fullnameList = fullnameList;
        this.usernameList = usernameList;
        this.emailList = emailList;
//        this.interest1List = interest1List;
//        this.interest2List = interest2List;
//        this.interest3List = interest3List;
//        this.bioList = bioList;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.activity_connection_item, null, true);

        TextView fullname = (TextView) rowView.findViewById(R.id.fullname);
        TextView username = (TextView) rowView.findViewById(R.id.username);
        TextView email = (TextView) rowView.findViewById(R.id.email);
//        TextView interest1 = (TextView) rowView.findViewById(R.id.interest1);
//        TextView interest2 = (TextView) rowView.findViewById(R.id.interest2);
//        TextView interest3 = (TextView) rowView.findViewById(R.id.interest3);
//        TextView bio = (TextView) rowView.findViewById(R.id.bio);

        fullname.setText(fullnameList.get(position));
        username.setText(usernameList.get(position));
        email.setText(emailList.get(position));
//        interest1.setText(interest1List.get(position));
//        interest2.setText(interest2List.get(position));
//        interest3.setText(interest3List.get(position));
//        bio.setText("\"" + bioList.get(position) + "\"");


        return rowView;

    }
}
