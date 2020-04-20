package com.example.alumninet;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class ForumListAdapter extends ArrayAdapter<String> {
    Activity context;
    ArrayList<String> titleList = new ArrayList<>();
    ArrayList<String> descriptionList = new ArrayList<>();
    ArrayList<String> dateList = new ArrayList<>();
    ArrayList<String> usernameList = new ArrayList<>();
    ArrayList<String> forumKeyList = new ArrayList<>();


    public ForumListAdapter(Activity context, ArrayList<String> titleList,
                            ArrayList<String> descriptionList, ArrayList<String> dateList,
                            ArrayList<String> usernameList, ArrayList<String> forumKeyList) {
        super(context, R.layout.activity_forum_item, titleList);
        // TODO Auto-generated constructor stub

        this.context = context;
        this.titleList = titleList;
        this.descriptionList = descriptionList;
        this.dateList = dateList;
        this.usernameList = usernameList;
        this.forumKeyList = forumKeyList;

    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.activity_forum_item, null, true);

        TextView titleText = (TextView) rowView.findViewById(R.id.title);
        TextView descriptionText = (TextView) rowView.findViewById(R.id.description);
        TextView username = (TextView) rowView.findViewById(R.id.username);
        TextView date = (TextView) rowView.findViewById(R.id.date);

        titleText.setText(titleList.get(position));
        descriptionText.setText(descriptionList.get(position));
        username.setText(usernameList.get(position));
        date.setText(dateList.get(position));

        return rowView;

        };
}
