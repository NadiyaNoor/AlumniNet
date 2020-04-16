package com.example.alumninet.ui.forums;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.alumninet.ForumListAdapter;
import com.example.alumninet.Forums;
import com.example.alumninet.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ForumsFragment extends Fragment {

    ListView listView;
    DatabaseReference databaseReference;
    ArrayList<String> forumList = new ArrayList<>();
    ArrayList<String> descriptionList = new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_forum, container, false);
        //View row = inflater.inflate(R.layout.activity_forum_item, container, false);

        //TextView title = root.findViewById(R.id.textView);
        //TextView description = root.findViewById(R.id.textView2);

        databaseReference = FirebaseDatabase.getInstance().getReference("Forums");
        listView = root.findViewById(R.id.forumList);
        arrayAdapter = new ForumListAdapter(getActivity(), forumList, descriptionList);
        listView.setAdapter(arrayAdapter);

        //title.setText();

        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String title = dataSnapshot.getValue(Forums.class).getTitle();
                String description = dataSnapshot.getValue(Forums.class).getDescription();
                //String description = dataSnapshot.getValue(Forums.class).toString();
                forumList.add(title);
                descriptionList.add(description);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

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

                Toast.makeText(getContext(),
                        "You Clicked: " +  listView.getItemAtPosition(position), Toast.LENGTH_LONG)
                        .show();

            }
        });
        return root;
    }
}