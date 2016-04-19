package com.itis4180.gbl.homework08.Activities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.itis4180.gbl.homework08.R;
import com.itis4180.gbl.homework08.Utils.User;

import java.util.HashMap;
import java.util.Map;

public class SignupActivity extends AppCompatActivity {

    private EditText nameText;
    private EditText emailText;
    private EditText phoneNumberText;
    private EditText passwordText;
    private EditText confirmPasswordText;

    private Button signupButton;
    private Button cancelButton;

    Firebase ref, users_ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        ref          = new Firebase(MainActivity.DATABASE_URL);
        users_ref    = ref.child("users");

        nameText            = (EditText) findViewById(R.id.signupNameText);
        emailText           = (EditText) findViewById(R.id.signupEmailText);
        phoneNumberText     = (EditText) findViewById(R.id.signupPhoneText);
        passwordText        = (EditText) findViewById(R.id.signupPasswordText);
        confirmPasswordText = (EditText) findViewById(R.id.signupPassword2Text);

        signupButton        = (Button) findViewById(R.id.signupButton2);
        cancelButton        = (Button) findViewById(R.id.signupCancelButton);


        signupButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                final String name         = nameText.getText().toString();
                final String email        = emailText.getText().toString();
                final String phone        = phoneNumberText.getText().toString();
                final String password1    = passwordText.getText().toString();
                String password2          = confirmPasswordText.getText().toString();

                if (password1.equals(password2)){

                    ref.createUser(email, password1, new Firebase.ValueResultHandler<Map<String, Object>>() {

                        @Override
                        public void onSuccess(Map<String, Object> result) {

                            Bitmap image = BitmapFactory.decodeResource(getBaseContext().getResources(), R.drawable.defaultprofile);
                            String uid = (String) result.get("uid");

                            User user = new User(name, email, phone, password1, uid, image);

                            Map<String, String> aux = new HashMap<>();

                            aux.put("name", user.getName());
                            aux.put("email", user.getEmail());
                            aux.put("phoneNumber", user.getPhoneNumber());
                            aux.put("password", user.getPassword());
                            aux.put("uid", user.getUid());
                            aux.put("picture", user.getPicture());

                            users_ref.push().setValue(aux);

                        }

                        @Override
                        public void onError(FirebaseError firebaseError) {
                            Toast.makeText(getApplicationContext(), firebaseError.getMessage(), Toast.LENGTH_SHORT).show();

                            switch (firebaseError.getCode()){

                                case(FirebaseError.EMAIL_TAKEN):
                                    Toast.makeText(getApplicationContext(), "Email already taken, choose another one!", Toast.LENGTH_SHORT).show();
                                    break;

                                default:
                                    break;
                            }

                            Log.d("Firebase", firebaseError.toString());
                        }
                    });

                    finish();
                }

                else {
                    Toast.makeText(getApplicationContext(), "Passwords do not match", Toast.LENGTH_SHORT).show();
                }
            }
        });


        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
