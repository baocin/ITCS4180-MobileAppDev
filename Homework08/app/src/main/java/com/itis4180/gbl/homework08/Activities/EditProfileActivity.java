package com.itis4180.gbl.homework08.Activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.itis4180.gbl.homework08.R;
import com.itis4180.gbl.homework08.Utils.User;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class EditProfileActivity extends AppCompatActivity {

    static final int PICK_PHOTO = 1;

    private String key;
    private User loggedInUser;
    private TextView nameView;
    private ImageView photoView;
    private EditText nameText, emailText, phoneText, passwordText;
    private Button updateButton, cancelButton;
    private Firebase ref, user_ref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);


        ref          = new Firebase(MainActivity.DATABASE_URL);

        loggedInUser = (User) getIntent().getSerializableExtra("USER");

        nameView        = (TextView) findViewById(R.id.nameView_Edit);
        photoView       = (ImageView) findViewById(R.id.imageField_Edit);
        nameText        = (EditText) findViewById(R.id.nameField_Edit);
        emailText       = (EditText) findViewById(R.id.emailField_Edit);
        phoneText       = (EditText) findViewById(R.id.phoneField_Edit);
        passwordText    = (EditText) findViewById(R.id.passwordField_edit);
        updateButton    = (Button) findViewById(R.id.updateButton_Edit);
        cancelButton    = (Button) findViewById(R.id.cancelButton_Edit);


        nameView.setText(loggedInUser.getName());
        nameText.setText(loggedInUser.getName());
        emailText.setText(loggedInUser.getEmail());
        phoneText.setText(loggedInUser.getPhoneNumber());
        passwordText.setTag(loggedInUser.getPassword());

        byte[] imageAsBytes = Base64.decode(loggedInUser.getPicture().getBytes(), Base64.DEFAULT);      // Decoding Image back to ByteArray
        photoView.setImageBitmap(BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length));  // Adding the image as a Bitmap

        photoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, PICK_PHOTO);
            }
        });

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                String name         = nameText.getText().toString();
                String email        = emailText.getText().toString();
                String phone        = phoneText.getText().toString();
                String password     = passwordText.getText().toString();

                loggedInUser.setName(name);
                loggedInUser.setEmail(email);
                loggedInUser.setPhoneNumber(phone);
                loggedInUser.setPassword(password);

                Bitmap image = ((BitmapDrawable)photoView.getDrawable()).getBitmap();
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                image.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                loggedInUser.setPicture(Base64.encodeToString(byteArray, Base64.DEFAULT));

                user_ref = ref.child("users");

                //TODO: ERROR HERE --> NOT GETTING KEY AND NEED TO CHANGE THE PASSWORD
                user_ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        for (DataSnapshot postSnapshot : snapshot.getChildren()) {

                            User user = postSnapshot.getValue(User.class);


                            if (user.getUid().equals(loggedInUser.getUid())) {
                                key = postSnapshot.getKey();
                                Log.d("KEY", key);


                                break;
                            }
                        }
                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError) {
                    }
                });

                Map<String, Object> map = new HashMap<>();
                map.put("name", loggedInUser.getName());
                map.put("email", loggedInUser.getEmail());
                map.put("phone", loggedInUser.getPhoneNumber());
                map.put("password", loggedInUser.getPassword());
                map.put("picture", loggedInUser.getPicture());
                user_ref.child(                user_ref.getAuth().getUid()).updateChildren(map);

                finish();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_PHOTO && resultCode == RESULT_OK) {

            Uri uri = data.getData();

            try {

                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                photoView.setImageBitmap(bitmap);

            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }
}
