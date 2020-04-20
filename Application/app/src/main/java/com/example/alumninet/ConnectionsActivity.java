package com.example.alumninet;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ConnectionsActivity extends AppCompatActivity {

    ListView listView;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users");
    ArrayAdapter<String> arrayAdapter;
    FirebaseAuth firebaseAuth = firebaseAuth = FirebaseAuth.getInstance();
    FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connection_profile);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        TextView fullname = (TextView) findViewById(R.id.fullname);
        TextView username = (TextView) findViewById(R.id.username);
        TextView email = (TextView) findViewById(R.id.email);
        TextView interest1 = (TextView) findViewById(R.id.interest1);
        TextView interest2 = (TextView) findViewById(R.id.interest2);
        TextView interest3 = (TextView) findViewById(R.id.interest3);
        TextView bio = (TextView) findViewById(R.id.bio);

        Intent intent = getIntent();
        String fullnameText = intent.getStringExtra("Fullname");
        String usernameText = intent.getStringExtra("Username");
        String emailText = intent.getStringExtra("Email");
        String interest1Text = intent.getStringExtra("Interest1");
        String interest2Text = intent.getStringExtra("Interest2");
        String interest3Text = intent.getStringExtra("Interest3");
        String bioText = intent.getStringExtra("Bio");

        fullname.setText(fullnameText);
        username.setText(usernameText);
        email.setText("Email me at: " + emailText);
        interest1.setText(interest1Text);
        interest2.setText(interest2Text);
        interest3.setText(interest3Text);
        bio.setText("\"" + bioText + "\"");

    }

}