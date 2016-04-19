package com.github.baocin.inclass12.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.github.baocin.inclass12.R;
import com.github.baocin.inclass12.User;

import java.util.HashMap;
import java.util.Map;


public class create_account extends Fragment{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_create_account, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Firebase.setAndroidContext(getActivity().getApplicationContext());
        final Firebase ref = new Firebase("https://inclass012.firebaseio.com/");
        final Firebase users_ref    = ref.child("users");

        getActivity().findViewById(R.id.signUpButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name = ((EditText) getActivity().findViewById(R.id.nameField)).getText().toString();
                final String email = ((EditText) getActivity().findViewById(R.id.emailField)).getText().toString();
                final String password = ((EditText) getActivity().findViewById(R.id.passwordField)).getText().toString();
                final User newUser = new User(name, email, password);

                ref.createUser(email, password, new Firebase.ValueResultHandler<Map<String, Object>>() {
                    @Override
                    public void onSuccess(Map<String, Object> result) {
                        Log.d("firebase", "Successfully created user account with uid: " + result.get("uid"));

                        if (ref == null){
                            Log.d("lksdjf", "Ref is NULL!");
                        }
                        Map<String, Object> map = new HashMap<String, Object>();
                        map.put("email", email);
                        map.put("fullName", name);
                        map.put("password", password);
                        map.put("userID", result.get("uid"));// ref.getAuth().getUid());
                        Log.d("map", map.toString());

                        users_ref.push().setValue(map);

                        Toast.makeText(getActivity().getApplicationContext(), "Successfully created user account with uid: " + result.get("uid"), Toast.LENGTH_SHORT).show();

                        getFragmentManager().popBackStack();

                    }

                    @Override
                    public void onError(FirebaseError firebaseError) {
                        // there was an error
                        Toast.makeText(getActivity().getApplicationContext(), firebaseError.toString(), Toast.LENGTH_SHORT).show();
                        Log.d("Firebase on Error", firebaseError.toString());
                    }
                });

                //First Login with newly made account
                ref.authWithPassword(email, password, new Firebase.AuthResultHandler() {
                    @Override
                    public void onAuthenticated(AuthData authData) {
                        // Authentication just completed successfully :)
                        Log.d("Firebase", "User ID: " + authData.getUid() + ", Provider: " + authData.getProvider());


                    }

                    @Override
                    public void onAuthenticationError(FirebaseError firebaseError) {
                        // there was an error
                        Log.d("Firebase", firebaseError.toString());

                    }
                });

            }
        });

        getActivity().findViewById(R.id.cancelButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
                //getActivity().finish();

            }
        });
    }
}
