package com.github.baocin.inclass11;

////File:MainActivity
////InClass 11
////Group 18
////4-11-16
////Praveenkumar Sangalad
////Michael Pedersen
////Gabriel Lima

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Firebase.setAndroidContext(getApplicationContext());
        final Firebase ref = new Firebase("https://inclass11.firebaseio.com/");

        Firebase.setAndroidContext(this);
        findViewById(R.id.loginButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = ((EditText) findViewById(R.id.email)).getText().toString();
                String password = ((EditText) findViewById(R.id.password)).getText().toString();

                ref.authWithPassword(email, password, new Firebase.AuthResultHandler() {
                    @Override
                    public void onAuthenticated(AuthData authData) {
                        System.out.println("User ID: " + authData.getUid() + ", Provider: " + authData.getProvider());
                        Toast.makeText(getApplicationContext(), "Logged in Successfully", Toast.LENGTH_SHORT).show();
                        Intent n = new Intent(MainActivity.this, ExpenseList.class);
                        startActivity(n);

                    }

                    @Override
                    public void onAuthenticationError(FirebaseError firebaseError) {
                        // there was an error
                        Toast.makeText(getApplicationContext(), firebaseError.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

        findViewById(R.id.createAccountButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent n = new Intent(MainActivity.this, CreateAccount.class);
                startActivity(n);
                //ref.child(name.getText().toString()).setValue(newUser);
            }
        });

        /*
        ref.addAuthStateListener(new Firebase.AuthStateListener() {
            @Override
            public void onAuthStateChanged(AuthData authData) {
                if (authData != null) {
                    // user is logged in
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("provider", authData.getProvider());
                    if(authData.getProviderData().containsKey("displayName")) {
                        map.put("displayName", authData.getProviderData().get("displayName").toString());
                    }
                    ref.child("users").child(authData.getUid()).setValue(map);
                } else {
                    // user is not logged in
                }
            }
        });

        ref.authWithPassword("jenny@example.com", "correcthorsebatterystaple",
            new Firebase.AuthResultHandler() {
            @Override
            public void onAuthenticated(AuthData authData) {}

            @Override
            public void onAuthenticationError(FirebaseError error) {
                // Something went wrong :(
                switch (error.getCode()) {
                    case FirebaseError.USER_DOES_NOT_EXIST:
                        // handle a non existing user
                        break;
                    case FirebaseError.INVALID_PASSWORD:
                        // handle an invalid password
                        break;
                    default:
                        // handle other errors
                        break;
                }
            }
        });


        AuthData authData = ref.getAuth();
        if (authData != null) {
            // user authenticated
        } else {
            // no user authenticated
        }


        //register
        //ref.authWithPassword("jenny@example.com", "correcthorsebatterystaple", authResultHandler);

        //Logout
        //ref.unauth();
         */
    }


}
