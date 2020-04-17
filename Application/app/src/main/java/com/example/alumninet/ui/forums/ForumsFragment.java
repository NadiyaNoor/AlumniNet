package com.example.alumninet.ui.forums;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.alumninet.ForumListAdapter;
import com.example.alumninet.Forums;
import com.example.alumninet.ForumsActivity;
import com.example.alumninet.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Collections;

public class ForumsFragment extends Fragment {

    ListView listView;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Forums");;
    ArrayList<String> titleList = new ArrayList<>();
    ArrayList<String> descriptionList = new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_forum, container, false);
        //View row = inflater.inflate(R.layout.activity_forum_item, container, false);

        //TextView title = root.findViewById(R.id.textView);
        //TextView description = root.findViewById(R.id.textView2);

        //databaseReference = FirebaseDatabase.getInstance().getReference("Forums");
        listView = root.findViewById(R.id.forumList);
        arrayAdapter = new ForumListAdapter(getActivity(), titleList, descriptionList);
        listView.setAdapter(arrayAdapter);

        //title.setText();

        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String title = dataSnapshot.getValue(Forums.class).getTitle();
                String description = dataSnapshot.getValue(Forums.class).getDescription();
                //String description = dataSnapshot.getValue(Forums.class).toString();
                titleList.add(title);
                descriptionList.add(description);
                Collections.reverse(titleList);
                Collections.reverse(descriptionList);
                arrayAdapter.notifyDataSetChanged();


            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String title = dataSnapshot.getValue(Forums.class).getTitle();
                String description = dataSnapshot.getValue(Forums.class).getDescription();
                //String description = dataSnapshot.getValue(Forums.class).toString();
                titleList.add(title);
                descriptionList.add(description);
                Collections.reverse(titleList);
                Collections.reverse(descriptionList);
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
                        "You Clicked: " + titleList.get(position) , Toast.LENGTH_LONG)
                        .show();

                Intent intent = new Intent(getActivity(), ForumsActivity.class);

                intent.putExtra("Title", titleList.get(position));
                intent.putExtra("Description", descriptionList.get(position));
                startActivity(intent);

            }
        });


        FloatingActionButton floatingActionButton = root.findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Create New Forum");
                // Set up the input
                // Add a TextView here for the "Title" label, as noted in the comments
                LinearLayout layout = new LinearLayout(getActivity());
                layout.setOrientation(LinearLayout.VERTICAL);
                final EditText title = new EditText(getActivity());
                title.setHint("Title");
                layout.addView(title); // Notice this is an add method

                // Add another TextView here for the "Description" label
                final EditText description = new EditText(getActivity());
                description.setHint("Description");
                layout.addView(description); // Another add method

                builder.setView(layout);

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Forums forum = new Forums(title.getText().toString(),
                                description.getText().toString());
                        addForum(forum);

                        }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();

            }
        });


        return root;
    }


    private void addForum(Forums forum) {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Forums").push();

        // get post unique ID and update post key
        String key = myRef.getKey();
        forum.setPostKey(key);


        // add post data to firebase database
        myRef.setValue(forum).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(getContext(),
                        "Posted Successfully ", Toast.LENGTH_LONG)
                        .show();
            }
        });
    }
}