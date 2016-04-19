package com.itis4180.gbl.homework08.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.itis4180.gbl.homework08.R;

public class MainActivity extends AppCompatActivity {

    static final String DATABASE_URL = "https://hw08.firebaseio.com/";//https://dazzling-heat-9273.firebaseio.com/";

    private EditText loginText;
    private EditText passwordText;
    private Button loginButton;
    private Button signupButton;
    private Firebase ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Firebase.setAndroidContext(getApplicationContext());
        Firebase.setAndroidContext(this);
        ref = new Firebase(DATABASE_URL);

        loginText       = (EditText) findViewById(R.id.loginMailText);
        passwordText    = (EditText) findViewById(R.id.loginPassText);
        loginButton     = (Button)   findViewById(R.id.loginButton);
        signupButton    = (Button)   findViewById(R.id.signupButton);

        AuthData authData = ref.getAuth();

        if (authData != null) {
            Intent i = new Intent(getBaseContext(), ConversationsActivity.class);
            i.putExtra("USER_UID", authData.getUid());
            startActivity(i);
        }

        loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String login        = loginText.getText().toString();
                String password     = passwordText.getText().toString();

                ref.authWithPassword(login, password, new Firebase.AuthResultHandler() {
                    @Override
                    public void onAuthenticated(AuthData authData) {
                        System.out.println("User ID: " + authData.getUid() + ", Provider: " + authData.getProvider());

                        Toast.makeText(getApplicationContext(), "Logged in Successfully", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(getBaseContext(), ConversationsActivity.class);
                        i.putExtra("USER_UID", authData.getUid());
                        startActivity(i);

                    }

                    @Override
                    public void onAuthenticationError(FirebaseError firebaseError) {
                        Toast.makeText(getApplicationContext(), firebaseError.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getBaseContext(), SignupActivity.class);
                startActivity(i);
            }
        });

    }
}
