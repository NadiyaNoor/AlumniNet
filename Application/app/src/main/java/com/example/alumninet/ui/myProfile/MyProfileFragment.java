package com.example.alumninet.ui.myProfile;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.alumninet.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.Random;

public class MyProfileFragment extends Fragment {

    private MyProfileViewModel myProfileViewModel;
    //private DatabaseReference mDatabase;
    // ...
    //mDatabase = FirebaseDatabase.getInstance().getReference();
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        myProfileViewModel =
                ViewModelProviders.of(this).get(MyProfileViewModel.class);
        View root = inflater.inflate(R.layout.fragment_my_profile, container, false);
        TextView userName = root.findViewById(R.id.userName);
        TextView userEmail = root.findViewById(R.id.userEmail);
        ImageView userImage = root.findViewById(R.id.userImage);
        /*final TextView textView = root.findViewById(R.id.text_my_profile);
        myProfileViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        /*UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
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
                });*/
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
        return root;
    }
}