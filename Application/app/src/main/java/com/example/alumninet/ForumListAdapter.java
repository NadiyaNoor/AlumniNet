package com.example.alumninet;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ForumListAdapter extends ArrayAdapter<String> {
    Activity context;
    ArrayList<String> titleList = new ArrayList<>();
    ArrayList<String> descriptionList = new ArrayList<>();

    public ForumListAdapter(Activity context, ArrayList<String> titleList, ArrayList<String> descriptionList) {
        super(context, R.layout.activity_forum_item, titleList);
        // TODO Auto-generated constructor stub

        this.context = context;
        this.titleList = titleList;
        this.descriptionList = descriptionList;

    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.activity_forum_item, null, true);

        TextView titleText = (TextView) rowView.findViewById(R.id.title);
        TextView descriptionText = (TextView) rowView.findViewById(R.id.description);

        titleText.setText(titleList.get(position));
        descriptionText.setText(descriptionList.get(position));

        return rowView;

        };
}
