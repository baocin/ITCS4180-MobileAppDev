package com.github.baocin.inclass12.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.github.baocin.inclass12.Expense;
import com.github.baocin.inclass12.R;


public class details extends Fragment{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_details, container, false);
    }

    public Expense e;
    Firebase ref2;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.deleteExpenses:
                Log.d("sdlkjfds", "lkdsjf" + e.toString());
                Log.d("key", "key" + e.getKey());
                ref2.child(ref2.getAuth().getUid()).child(e.getKey()).setValue(null);
                Toast.makeText(getActivity().getApplicationContext(), "DELETED YOUR EXPENSE!", Toast.LENGTH_SHORT).show();

                getFragmentManager().popBackStack();
                return true;
        };
        return true;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        Firebase.setAndroidContext(getActivity().getApplicationContext());
        final Firebase ref = new Firebase("https://inclass012.firebaseio.com/expenses");
        ref2 = ref;

        TextView name = (TextView) getActivity().findViewById(R.id.name);
        TextView cat = (TextView) getActivity().findViewById(R.id.cat);
        TextView amount = (TextView) getActivity().findViewById(R.id.amount);
        TextView date = (TextView) getActivity().findViewById(R.id.date);

        name.setText(e.getName());
        cat.setText(e.getCategory());
        amount.setText("$" + e.getAmount());
        date.setText(e.getDate());
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.actionbar, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

}
