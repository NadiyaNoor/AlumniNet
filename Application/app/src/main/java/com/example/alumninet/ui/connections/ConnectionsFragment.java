package com.example.alumninet.ui.connections;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.alumninet.Connection;
import com.example.alumninet.ConnectionAdapter;
import com.example.alumninet.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ConnectionsFragment extends Fragment {

    ListView listView;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users");
    ArrayList<String> fullnameList = new ArrayList<>();
    ArrayList<String> usernameList = new ArrayList<>();
    ArrayList<String> emailList = new ArrayList<>();
    ArrayList<String> interest1List = new ArrayList<>();
    ArrayList<String> interest2List = new ArrayList<>();
    ArrayList<String> interest3List = new ArrayList<>();
    ArrayList<String> bioList = new ArrayList<>();

    ArrayAdapter<String> arrayAdapter;
    FirebaseAuth firebaseAuth = firebaseAuth = FirebaseAuth.getInstance();
    FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_connections, container, false);

        listView = root.findViewById(R.id.connectionsList);
        arrayAdapter = new ConnectionAdapter(getActivity(), fullnameList, usernameList,
                emailList, interest1List, interest2List, interest3List, bioList);
        listView.setAdapter(arrayAdapter);

        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String fullname = dataSnapshot.getValue(Connection.class).getFullname();
                String username = dataSnapshot.getValue(Connection.class).getUsername();
                String email = dataSnapshot.getValue(Connection.class).getEmail();
                String interest1 = dataSnapshot.getValue(Connection.class).getInterest1();
                String interest2 = dataSnapshot.getValue(Connection.class).getInterest2();
                String interest3 = dataSnapshot.getValue(Connection.class).getInterest3();
                String bio = dataSnapshot.getValue(Connection.class).getBio();
                fullnameList.add(fullname);
                usernameList.add(username);
                emailList.add(email);
                interest1List.add(interest1);
                interest2List.add(interest2);
                interest3List.add(interest3);
                bioList.add(bio);

                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String fullname = dataSnapshot.getValue(Connection.class).getFullname();
                String username = dataSnapshot.getValue(Connection.class).getUsername();
                String email = dataSnapshot.getValue(Connection.class).getEmail();
                String interest1 = dataSnapshot.getValue(Connection.class).getInterest1();
                String interest2 = dataSnapshot.getValue(Connection.class).getInterest2();
                String interest3 = dataSnapshot.getValue(Connection.class).getInterest3();
                String bio = dataSnapshot.getValue(Connection.class).getBio();

                fullnameList.add(fullname);
                usernameList.add(username);
                emailList.add(email);
                interest1List.add(interest1);
                interest2List.add(interest2);
                interest3List.add(interest3);
                bioList.add(bio);

                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                /*Toast.makeText(getContext(),
                        "You Clicked: " +  listView.getItemAtPosition(position), Toast.LENGTH_LONG)
                        .show();*/
                Toast.makeText(getContext(),
                        "You Clicked: " + fullnameList.get(position), Toast.LENGTH_LONG)
                        .show();

//                Intent intent = new Intent(getActivity(), ConnectionActivity.class);
//
//                intent.putExtra("Fullname", fullnameList.get(position));
//                intent.putExtra("Username", usernameList.get(position));
//                intent.putExtra("Email", emailList.get(position));
//                startActivity(intent);

            }
        });

        return root;
    }


}