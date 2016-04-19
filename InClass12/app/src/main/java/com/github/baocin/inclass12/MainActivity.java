package com.github.baocin.inclass12;

////File:MainActivity
////InClass 12
////Group 18
////4-18-16
////Praveenkumar Sangalad
////Michael Pedersen
////Gabriel Lima

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.baocin.inclass12.fragments.login;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getFragmentManager().beginTransaction().add(R.id.fragment_container, new login(), "ExpenseList").commit();

    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0){
            getFragmentManager().popBackStack();
        }else{
            super.onBackPressed();
        }
    }
}
