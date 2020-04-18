package com.example.alumninet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GetTokenResult;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

   private EditText emailID, password, username, name;
    private TextView bio;

    private Spinner Drop1;
    private Spinner Drop2;
    private Spinner Drop3;
    private ProgressDialog loadingBar;
    private FirebaseAuth mAuth;
    private DatabaseReference UsersRef;
   // private FirebaseUser FBUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       //FirebaseUser FBUser = FirebaseAuth.getInstance().getCurrentUser();
     // String currentUserId = FBUser.getUid();
        mAuth = FirebaseAuth.getInstance();
        String uid = UUID.randomUUID().toString();

        UsersRef = FirebaseDatabase.getInstance().getReference("Users").child(uid);
        emailID = findViewById(R.id.email);
        password = findViewById(R.id.password);
        username = findViewById(R.id.username);
        name = findViewById(R.id.name);
        bio = findViewById(R.id.bio);
        Button btnSignUp = findViewById(R.id.signupbutton);
        TextView tvLogIn = findViewById(R.id.loginbutton);
        loadingBar = new ProgressDialog(this);
        Drop1 = findViewById(R.id.drop1);
        Drop2 = findViewById(R.id.drop2);
        Drop3 = findViewById(R.id.drop3);

        btnSignUp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                String email = emailID.getText().toString();
                String pwd = password.getText().toString();

                if (email.isEmpty()) {
                    emailID.setError("Please enter your email id");
                    emailID.requestFocus();
                } else if (pwd.isEmpty()) {
                    password.setError("Please enter your password");
                    password.requestFocus();
                } else if (email.isEmpty() && pwd.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Fields Are Empty!", Toast.LENGTH_SHORT).show();
                } else if (!(email.isEmpty() && pwd.isEmpty())) {
                    mAuth.createUserWithEmailAndPassword(email, pwd).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(MainActivity.this, "SignUp Unsuccessful, Please Try Again", Toast.LENGTH_SHORT).show();

                            } else {
                                SaveAccountSetupInformation();
                                startActivity(new Intent(MainActivity.this, NavBar.class));
                            }
                        }
                    });

                } else {
                    Toast.makeText(MainActivity.this, "Error Occurred!", Toast.LENGTH_SHORT).show();

                }

            }
        });

        tvLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(i);
            }


        });


    }


    public void SaveAccountSetupInformation() {
        String UserName = username.getText().toString();
        String FullName = name.getText().toString();
        String Bio = bio.getText().toString();
        String Email = emailID.getText().toString();
        if(TextUtils.isEmpty(UserName)){
            Toast.makeText(this, "Please write your username...", Toast.LENGTH_SHORT).show();
        }
        if(TextUtils.isEmpty(FullName)){
            Toast.makeText(this, "Please write your full Name...", Toast.LENGTH_SHORT).show();
        }
        if(TextUtils.isEmpty(Bio)){
            Toast.makeText(this, "Please write your bio...", Toast.LENGTH_SHORT).show();
        }
        else{
            loadingBar.setTitle("Creating New Account");
            loadingBar.setMessage("Please wait while we are creating your new Account");
            loadingBar.show();
            loadingBar.setCanceledOnTouchOutside(true);
            HashMap<String, Object> userMap = new HashMap<>();
            userMap.put("username", UserName);
            userMap.put("fullname", FullName);
            userMap.put("bio", Bio);
            userMap.put("email", Email);
            userMap.put("interest1", Drop1.getSelectedItem().toString());
            userMap.put("interest2",Drop2.getSelectedItem().toString());
            userMap.put("interest3", Drop3.getSelectedItem().toString());



            UsersRef.updateChildren(userMap).addOnCompleteListener((OnCompleteListener<Void>) task -> {
                if(task.isSuccessful()){
                    Toast.makeText(MainActivity.this, "Your Account has been successfully created... ", Toast.LENGTH_LONG).show();

                    SendUserToMainActivity();
                }
                else{
                    String message = Objects.requireNonNull(task.getException()).getMessage();
                    Toast.makeText(MainActivity.this, "Error Occurred" + message, Toast.LENGTH_SHORT).show();
                loadingBar.dismiss();

                }

            });

        }

    }

    private void SendUserToMainActivity() {
        Intent j = new Intent(MainActivity.this,NavBar.class);
        j.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(j);
        finish();
    }



}