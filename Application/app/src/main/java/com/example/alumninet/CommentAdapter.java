package com.example.alumninet;

import android.app.Activity;
import android.content.Context;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class CommentAdapter extends ArrayAdapter<String> {
    Activity context;
    //private Context mContext;
    ArrayList<String> commentList = new ArrayList<>();
    ArrayList<String> dateList = new ArrayList<>();
    ArrayList<String> usernameList = new ArrayList<>();
    //private ArrayList<Comment> mData;

    TextView tv_name,tv_content,tv_date;

    public CommentAdapter(Activity context, ArrayList<String> commentList, ArrayList<String> dateList,
                          ArrayList<String> usernameList) {
        super(context, R.layout.activity_comment_item, commentList);
        this.context = context;
        this.commentList = commentList;
        this.dateList = dateList;
        this.usernameList = usernameList;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View row = inflater.inflate(R.layout.activity_comment_item,parent,false);

        tv_name = row.findViewById(R.id.comment_username);
        tv_content = row.findViewById(R.id.comment_content);
        tv_date = row.findViewById(R.id.comment_date);

        tv_name.setText(usernameList.get(position));
        tv_content.setText(commentList.get(position));
        tv_date.setText(dateList.get(position));

        return row;
    }
/*
    @Override
    public void onBindViewHolder(@NonNull CommentViewHolder holder, int position) {

        holder.tv_name.setText(mData.get(position).getUname());
        holder.tv_content.setText(mData.get(position).getContent());
        holder.tv_date.setText(timestampToString((Long)mData.get(position).getTimestamp()));

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class CommentViewHolder extends RecyclerView.ViewHolder{
        TextView tv_name,tv_content,tv_date;

        public CommentViewHolder(View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.comment_username);
            tv_content = itemView.findViewById(R.id.comment_content);
            tv_date = itemView.findViewById(R.id.comment_date);
        }
    }

 */


//
//    private String timestampToString(long time) {
//
//        Calendar calendar = Calendar.getInstance(Locale.ENGLISH);
//        calendar.setTimeInMillis(time);
//        String date = DateFormat.format("hh:mm",calendar).toString();
//        return date;
//
//
//    }


}