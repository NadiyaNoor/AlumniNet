package com.example.alumninet.ui.myProfile;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.alumninet.Connection;
import com.example.alumninet.ConnectionsActivity;
import com.example.alumninet.EditProfileActivity;
import com.example.alumninet.LoginActivity;
import com.example.alumninet.MainActivity;
import com.example.alumninet.NavBar;
import com.example.alumninet.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.Random;

public class MyProfileFragment extends Fragment {

    private MyProfileViewModel myProfileViewModel;

    FirebaseAuth firebaseAuth = firebaseAuth = FirebaseAuth.getInstance();
    FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users/"
            + firebaseUser.getUid());

    //private DatabaseReference mDatabase;
    // ...
    //mDatabase = FirebaseDatabase.getInstance().getReference();
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        myProfileViewModel =
                ViewModelProviders.of(this).get(MyProfileViewModel.class);
        View root = inflater.inflate(R.layout.fragment_my_profile, container, false);
        TextView fullname = (TextView) root.findViewById(R.id.fullname);
        TextView username = (TextView) root.findViewById(R.id.username);
        TextView email = (TextView) root.findViewById(R.id.email);
        TextView interest1 = (TextView) root.findViewById(R.id.interest1);
        TextView interest2 = (TextView) root.findViewById(R.id.interest2);
        TextView interest3 = (TextView) root.findViewById(R.id.interest3);
        TextView bio = (TextView) root.findViewById(R.id.bio);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String fullnameT = dataSnapshot.getValue(Connection.class).getFullname();
                String usernameT = dataSnapshot.getValue(Connection.class).getUsername();
                String emailT = dataSnapshot.getValue(Connection.class).getEmail();
                String uidT = dataSnapshot.getKey().toString();
                String interest1T = dataSnapshot.getValue(Connection.class).getInterest1();
                String interest2T = dataSnapshot.getValue(Connection.class).getInterest2();
                String interest3T = dataSnapshot.getValue(Connection.class).getInterest3();
                String bioT = dataSnapshot.getValue(Connection.class).getBio();

                fullname.setText(fullnameT);
                username.setText(usernameT);
                email.setText(emailT);
                interest1.setText(interest1T);
                interest2.setText(interest2T);
                interest3.setText(interest3T);
                bio.setText(bioT);
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
            });

        Button btnLogout = root.findViewById(R.id.logoffbutton);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent inToMain = new Intent(getActivity(), LoginActivity.class);
                startActivity(inToMain);
            }
        });

        ImageView settings = root.findViewById(R.id.settings);
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), EditProfileActivity.class);

                intent.putExtra("Fullname", fullname.getText());
                intent.putExtra("Username", username.getText());
                intent.putExtra("Email", email.getText());
                intent.putExtra("Interest1", interest1.getText());
                intent.putExtra("Interest2", interest2.getText());
                intent.putExtra("Interest3", interest3.getText());
                intent.putExtra("Bio", bio.getText());

                startActivity(intent);

            }
        });


        /*final TextView textView = root.findViewById(R.id.text_my_profile);
        myProfileViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
        /*
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName("Jorge Quezada")
                .setPhotoUri(Uri.parse("https://media-exp1.licdn.com/dms/image/C4E03AQGcupC9JPgitw/profile-displayphoto-shrink_200_200/0?e=1592438400&v=beta&t=dvcMfY-DnUawpaLs30S7uOmeLvCbtQ5p5yVK7cawapU"))
                .build();

        user.updateProfile(profileUpdates)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    private static final String TAG = "Random";

                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "User profile updated.");
                        }
                    }
                });
        if (user != null) {
            // Name, email address, and profile photo Url
            String name = user.getDisplayName();
            String email = user.getEmail();
            Uri photoUrl = user.getPhotoUrl();

            // Check if user's email is verified
            boolean emailVerified = user.isEmailVerified();

            // The user's ID, unique to the Firebase project. Do NOT use this value to
            // authenticate with your backend server, if you have one. Use
            // FirebaseUser.getIdToken() instead.
            String uid = user.getUid();
            //Get userName to user profile...
            userName.setText(name); //Add after initialized
            userEmail.setText(email);
            userImage.setImageURI(photoUrl);

        }
        */

        return root;
        }
    }