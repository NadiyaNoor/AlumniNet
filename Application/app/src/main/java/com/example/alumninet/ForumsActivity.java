package com.example.alumninet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ForumsActivity extends AppCompatActivity {

    //Activity activity;
    //FirebaseAuth firebaseAuth;
    //FirebaseUser firebaseUser;
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

    EditText editTextComment;
    Button btnAddComment;

    ListView listView;
    //CommentAdapter <String> commentAdapter;
    ArrayAdapter<String> commentAdapter;
    //ArrayList<Comment> listComment;
    ArrayList<String> commentList = new ArrayList<>();
    ArrayList<String> dateList = new ArrayList<>();
    ArrayList<String> usernameList = new ArrayList<>();

    FirebaseAuth firebaseAuth = firebaseAuth = FirebaseAuth.getInstance();

    FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

    DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("Users/" +
            firebaseUser.getUid());

    DatabaseReference commentReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forums);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        Intent intent = getIntent();
        String titleText = intent.getStringExtra("Title");
        String descriptionText = intent.getStringExtra("Description");
        String dateText = intent.getStringExtra("Date");
        String usernameText = intent.getStringExtra("Username");
        String forumKey = intent.getStringExtra("ForumKey");

        //commentReference = firebaseDatabase.getReference("Forums/" + forumKey);
        commentReference = firebaseDatabase.getReference("Comments/" + forumKey);
        TextView title = findViewById(R.id.title);
        TextView description = findViewById(R.id.description);
        TextView username = findViewById(R.id.username);
        TextView date = findViewById(R.id.date);
        btnAddComment = findViewById(R.id.btnAddComment);
        editTextComment = findViewById(R.id.editTextComment);

        title.setText(titleText);
        description.setText(descriptionText);
        username.setText(usernameText);
        date.setText(dateText);

        //TextView textView = findViewById(R.id.textView2);

        listView = findViewById(R.id.rv_comment);

        commentAdapter = new CommentAdapter(this, commentList,
                dateList, usernameList);
        listView.setAdapter(commentAdapter);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance();


//            @Override
//            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//                String comment = dataSnapshot.getValue(Comment.class).getComment();
//                String date = dataSnapshot.getValue(Comment.class).getDate();
//                String username = dataSnapshot.getValue(Comment.class).getUid();
//
//                commentList.add(comment);
//                dateList.add(date);
//                usernameList.add(username);
//
//                commentAdapter.notifyDataSetChanged();

//                listComment = new ArrayList<>();
//                for (DataSnapshot snap:dataSnapshot.getChildren()) {
//                    Comment comment = snap.getValue(Comment.class);
//                    listComment.add(comment) ;
//
//                }

        // commentAdapter.notifyDataSetChanged();
        commentReference.addValueEventListener(new ValueEventListener() {
            @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot key : dataSnapshot.getChildren()) {

                        //for (DataSnapshot item : key.getChildren()) {

                        String comment = key.child("comment").getValue(String.class);
                        String date = key.child("date").getValue(String.class);
                        String username = key.child("username").getValue(String.class);
                        // String date = item.getValue().toString();
                        //String username = item.getValue(Comment.class).getUid();
                        //for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        //dataSnapshot.getChildren();
                        //commentReference.getKey();
                        commentList.add(comment);
                        dateList.add(date);
                        usernameList.add(username);
                        //}
                    }
//                String comment = dataSnapshot.getValue(Comment.class).getComment();
//                String date = dataSnapshot.getValue(Comment.class).getDate();
//                String username = dataSnapshot.getValue(Comment.class).getUid();
//                //for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
//                    dataSnapshot.getChildren();
//                    commentReference.getKey();
//                    commentList.add(comment);
//                dateList.add(date);
//                usernameList.add(username);
                //textView.setText(comment);
                //commentReference.getKey();
                commentAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

//        commentReference.setValue(comment).addOnSuccessListener(new OnSuccessListener<Void>() {
//            @Override
//            public void onSuccess(Void aVoid) {
//                //showMessage("comment added");
//                editTextComment.setText("");
//                btnAddComment.setVisibility(View.VISIBLE);
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                //showMessage("fail to add comment : "+e.getMessage());
//            }
//        });
//
//    }


        btnAddComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        SimpleDateFormat sdf = new SimpleDateFormat("h:mm a \t MM/dd/y");
                        //comment.setDate(sdf.format(new Date()));
                        Comment comment = new Comment(editTextComment.getText().toString(),
                                dataSnapshot.child("username").getValue(String.class), sdf.format(new Date()));

                        editTextComment.setText("");


                        addComment(comment, forumKey);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }

                });
            }
        });
    }

    private void addComment(Comment comment, String forumKey) {

        //FirebaseDatabase database = FirebaseDatabase.getInstance();
        commentReference = firebaseDatabase.getReference("Comments/" + forumKey).push();
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("h:mm a \t MM/dd/y");

        comment.setDate(sdf.format(new Date()));


        // add post data to firebase database
        commentReference.setValue(comment).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(getApplicationContext(),
                        "Posted Successfully ", Toast.LENGTH_LONG)
                        .show();
            }
        });
    }
}
//                DatabaseReference commentReference = firebaseDatabase.getReference("Forums/" + ForumKey).child("Comments").push();
//                String comment_content = editTextComment.getText().toString();
//                String uid = firebaseUser.getUid();
//                String uname = firebaseUser.getDisplayName();
//                Comment comment = new Comment(comment_content, uid, uname);
//commentAdapter = new CommentAdapter(getApplicationContext(), listComment);
//RvComment.setAdapter(commentAdapter);

//commentAdapter.notifyDataSetChanged();


//DatabaseReference commentRef = firebaseDatabase.getReference("Comment").child("Nadiya");


//iniRvComment();

//RvComment.setLayoutManager(new LinearLayoutManager(this));


//    private void iniRvComment() {
//    }
//
//    private void showMessage(String message) {
//
//        Toast.makeText(this,message,Toast.LENGTH_LONG).show();
//
//    }


//    private String timestampToString(long time) {
//
//        Calendar calendar = Calendar.getInstance(Locale.ENGLISH);
//        calendar.setTimeInMillis(time);
//        String date = DateFormat.format("dd-MM-yyyy",calendar).toString();
//        return date;
//
//
//    }
