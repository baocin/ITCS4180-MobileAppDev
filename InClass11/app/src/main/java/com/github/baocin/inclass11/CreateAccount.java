package com.github.baocin.inclass11;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.HashMap;
import java.util.Map;

public class CreateAccount extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        Firebase.setAndroidContext(getApplicationContext());
        final Firebase ref = new Firebase("https://inclass11.firebaseio.com/");

        findViewById(R.id.signUpButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name = ((EditText) findViewById(R.id.nameField)).getText().toString();
                final String email = ((EditText) findViewById(R.id.emailField)).getText().toString();
                final String password = ((EditText) findViewById(R.id.passwordField)).getText().toString();
                final User newUser = new User(name, email, password);

                ref.createUser(email, password, new Firebase.ValueResultHandler<Map<String, Object>>() {
                    @Override
                    public void onSuccess(Map<String, Object> result) {
                        Log.d("firebase", "Successfully created user account with uid: " + result.get("uid"));
                        Toast.makeText(getApplicationContext(), "Successfully created user account with uid: " + result.get("uid"), Toast.LENGTH_SHORT).show();


                    }

                    @Override
                    public void onError(FirebaseError firebaseError) {
                        // there was an error
                        Toast.makeText(getApplicationContext(), firebaseError.toString(), Toast.LENGTH_SHORT).show();
                        Log.d("Firebase", firebaseError.toString());
                    }
                });

                //First Login with newly made account
                ref.authWithPassword(email, password, new Firebase.AuthResultHandler() {
                    @Override
                    public void onAuthenticated(AuthData authData) {
                        // Authentication just completed successfully :)
                        Log.d("Firebase", "User ID: " + authData.getUid() + ", Provider: " + authData.getProvider());

                        Map<String, String> map = new HashMap<String, String>();
                        map.put("email", email);
                        map.put("fullName", name);
                        map.put("password", password);
                        map.put("userID", ref.getAuth().getUid());
                        ref.child("users").push().setValue(map);

                    }
                    @Override
                    public void onAuthenticationError(FirebaseError firebaseError) {
                        // there was an error
                        Log.d("Firebase", firebaseError.toString());

                    }
                });

            }
        });

        findViewById(R.id.cancelButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });
    }
}
