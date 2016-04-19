package com.github.baocin.inclass12.fragments;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.github.baocin.inclass12.R;


public class login extends Fragment{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container,false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //fragmentManager.beginTransaction().replace(R.id.fragment,new login()).commit();
        Firebase.setAndroidContext(getActivity().getApplicationContext());
        final Firebase ref = new Firebase("https://inclass012.firebaseio.com/");

        getActivity().findViewById(R.id.loginButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = ((EditText) getActivity().findViewById(R.id.email)).getText().toString();
                String password = ((EditText) getActivity().findViewById(R.id.password)).getText().toString();

                ref.authWithPassword(email, password, new Firebase.AuthResultHandler() {
                    @Override
                    public void onAuthenticated(AuthData authData) {
                        System.out.println("User ID: " + authData.getUid() + ", Provider: " + authData.getProvider());
                        Toast.makeText(getActivity().getApplicationContext(), "Logged in Successfully", Toast.LENGTH_SHORT).show();
                        //Intent n = new Intent(MainActivity.this, ExpenseList.class);
                        //startActivity(n);


                        FragmentTransaction transaction = getActivity().getFragmentManager().beginTransaction();
                        transaction.replace(R.id.fragment_container, new expense_list());
                        transaction.addToBackStack(null);
                        transaction.commit();

                        //getActivity().getFragmentManager().beginTransaction().replace(R.id.fragment_container, new expense_list(), "ExpenseList").commit();


                    }

                    @Override
                    public void onAuthenticationError(FirebaseError firebaseError) {
                        // there was an error
                        Toast.makeText(getActivity().getApplicationContext(), firebaseError.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

        getActivity().findViewById(R.id.createAccountButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent n = new Intent(getActivity(), CreateAccount.class);
                //startActivity(n);

                FragmentTransaction transaction = getActivity().getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, new create_account());
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

    }


}
