package com.github.baocin.inclass12.fragments;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.github.baocin.inclass12.Expense;
import com.github.baocin.inclass12.ExpenseAdapter;
import com.github.baocin.inclass12.R;

import java.util.ArrayList;
import java.util.HashMap;


public class expense_list extends Fragment{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);

        return inflater.inflate(R.layout.fragment_expense_list, container, false);
    }


    Firebase ref2;
    ArrayList<Expense> exp;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        Firebase.setAndroidContext(getActivity().getApplicationContext());
        final Firebase ref = new Firebase("https://inclass012.firebaseio.com/expenses");
        ref2 = ref;

        if (ref.getAuth() != null){
            //authenticated

            //Query queryRef = ref.orderByChild("user");

            //get expense data
            ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    Log.d("FirebaseData", "dsfds"); //snapshot.getValue().toString()
                    //List<Expense> value = (List<Expense>) snapshot.getValue();
                    //Log.d("Firebase data", value);

                    exp = new ArrayList<Expense>();
                    for (DataSnapshot ds : snapshot.getChildren()){
                        //Check if user == currently longged in user
                        HashMap<String, HashMap<String, String>> hm = (HashMap<String,HashMap<String, String>>) ds.getValue();
                        Log.d("FirebaseData", hm.toString());
                        for (String key : hm.keySet()){
                            HashMap<String, String> item = hm.get(key);
                            if (item.get("user").equals(ref.getAuth().getUid())){
                                //Logged in user's data
                                //Add to ArrayList
                                Expense k = new Expense(item.get("amount"), item.get("category"), item.get("date"), item.get("name"), item.get("user"));
                                k.setKey(key);
                                exp.add(k);

                            }
                            for (String k : item.keySet()){
                                //Log.d("FirebaseData", key + " - " + k + " : " + item.get(k));

                            }

                        }
                    }


                    for (Expense e : exp){
                        Log.d("Data", e.toString());
                    }

                    try {
                        ListView expenseList = (ListView) getActivity().findViewById(R.id.listView);
                        ExpenseAdapter adapter = new ExpenseAdapter(getActivity().getApplicationContext(), exp);
                        expenseList.setAdapter(adapter);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {
                    System.out.println("The read failed: " + firebaseError.getMessage());
                }
            });

        }else{
            //unathenticated


        }

        ListView v = (ListView) getActivity().findViewById(R.id.listView);
        v.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Intent n = new Intent(getActivity(), Details.class);
                //n.putExtra("exp", exp.get(position));
                //startActivity(n);

                FragmentTransaction transaction = getActivity().getFragmentManager().beginTransaction();
                details f = new details();
                f.e = exp.get(position);
                transaction.replace(R.id.fragment_container,f );
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        //getMenuInflater().inflate(R.menu.actionbar2, menu);
        inflater.inflate(R.menu.actionbar2, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.addExpenses:
                //Intent i = new Intent(getActivity(), AddExpense.class);
                //startActivity(i);
                FragmentTransaction transaction = getActivity().getFragmentManager().beginTransaction();
                add_expense e = new add_expense();
                transaction.replace(R.id.fragment_container, e);
                transaction.addToBackStack(null);
                transaction.commit();

                return true;
            case R.id.logout:
                //Logout
                Log.d("Firebase", "logout");
                ref2.unauth();
                getFragmentManager().popBackStack();

                return true;
        };
        return true;
    }
}
