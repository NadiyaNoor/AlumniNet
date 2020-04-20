package com.example.alumninet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class EditProfileActivity extends AppCompatActivity {
    FirebaseAuth firebaseAuth = firebaseAuth = FirebaseAuth.getInstance();
    FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users/"
            + firebaseUser.getUid());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        EditText fullname = (EditText) findViewById(R.id.fullname);
        EditText username = (EditText) findViewById(R.id.username);
        TextView email = (TextView) findViewById(R.id.email);
        Spinner interest1 = (Spinner) findViewById(R.id.interest1);
        Spinner interest2 = (Spinner) findViewById(R.id.interest2);
        Spinner interest3 = (Spinner) findViewById(R.id.interest3);
        EditText bio = (EditText) findViewById(R.id.bio);
        Button save = (Button) findViewById(R.id.save);

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
        email.setText(emailText);

        String compareValue = interest1Text;
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.my_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        interest1.setAdapter(adapter);
        if (compareValue != null) {
            int spinnerPosition = adapter.getPosition(compareValue);
            interest1.setSelection(spinnerPosition);
        }

        compareValue = interest2Text;
        interest2.setAdapter(adapter);
        if (compareValue != null) {
            int spinnerPosition = adapter.getPosition(compareValue);
            interest2.setSelection(spinnerPosition);
        }

        compareValue = interest3Text;
        interest3.setAdapter(adapter);
        if (compareValue != null) {
            int spinnerPosition = adapter.getPosition(compareValue);
            interest3.setSelection(spinnerPosition);
        }

        bio.setText(bioText);


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, Object> update = new  HashMap<String,Object>();
                update.put("fullname", fullname.getText().toString());
                update.put("username", username.getText().toString());
                update.put("email", email.getText().toString());
                update.put("interest1", interest1.getSelectedItem().toString());
                update.put("interest2", interest2.getSelectedItem().toString());
                update.put("interest3", interest3.getSelectedItem().toString());
                update.put("bio", bio.getText().toString());

                databaseReference.updateChildren(update);

                Toast.makeText(getApplicationContext(),
                        "Updated Successfully ", Toast.LENGTH_LONG)
                        .show();

                Intent intent = new Intent(getApplicationContext(), NavBar.class);
                startActivity(intent);

            }

        });
    }
}
