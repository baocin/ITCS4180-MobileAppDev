package com.itis4180.gbl.homework08.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.itis4180.gbl.homework08.R;
import com.itis4180.gbl.homework08.Utils.User;
import com.itis4180.gbl.homework08.Utils.UserAdapter;

import java.util.ArrayList;

public class ConversationsActivity extends AppCompatActivity {

    private User loggedInUser;

    private Firebase users_ref;
    private String loggedInUserID;

    private ArrayList<User> usersList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversations);

        UserAdapter adapter;
        ListView conversationsList;

        Firebase.setAndroidContext(this);

        /** Initializations **/
        usersList           = new ArrayList<>();
        users_ref           = new Firebase(MainActivity.DATABASE_URL + "/users");
        loggedInUserID      = getIntent().getStringExtra("USER_UID");
        conversationsList   = (ListView) findViewById(R.id.contactsListView);

        /** Firebase **/
        if (users_ref.getAuth() != null) {
            users_ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                        User user = postSnapshot.getValue(User.class);
                        if (!user.getUid().equals(loggedInUserID)) {
                            Log.d("USERS_ADDED", user.toString());
                            usersList.add(user);

                        } else {
                            loggedInUser = user;
                        }
                    }
                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {
                    System.out.println("The read failed: " + firebaseError.getMessage());

                }
            });
        }

        /** Activity Configuration **/

        //Set ListView Data
        adapter             = new UserAdapter(getApplicationContext(), usersList);
        conversationsList.setAdapter(adapter); //TODO: ERROR HERE --> ARRAY ADAPTER ONLY WORKS WHEN IT WANTS
        adapter.setNotifyOnChange(true);

        conversationsList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_bar, menu);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.edit_profile:

                Intent i = new Intent(getBaseContext(), EditProfileActivity.class);
                i.putExtra("USER", loggedInUser);
                startActivity(i);

                return true;

            case R.id.logout:
                users_ref.unauth();
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }
}
