package com.github.baocin.inclass11;

////InClass 11
////Group 18
////4-11-16
////Praveenkumar Sangalad
////Michael Pedersen
////Gabriel Lima

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class ExpenseList extends AppCompatActivity {
    Firebase ref2;
    ArrayList<Expense> exp;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar2, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.addExpenses:
                Intent i = new Intent(ExpenseList.this, AddExpense.class);
                startActivity(i);
                return true;
            case R.id.logout:
                //Logout
                Log.d("Firebase", "logout");
                ref2.unauth();
                finish();
                return true;
        };
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_list);

        Firebase.setAndroidContext(getApplicationContext());
        final Firebase ref = new Firebase("https://inclass11.firebaseio.com/expenses");
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


                    ListView expenseList = (ListView) findViewById(R.id.listView);

                    for (Expense e : exp){
                        Log.d("Data", e.toString());
                    }

                    ExpenseAdapter adapter = new ExpenseAdapter(getApplicationContext(), exp);
                    expenseList.setAdapter(adapter);
                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {
                    System.out.println("The read failed: " + firebaseError.getMessage());
                }
            });

        }else{
            //unathenticated


        }

        ListView v = (ListView) findViewById(R.id.listView);
        v.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent n = new Intent(ExpenseList.this, Details.class);
                n.putExtra("exp", exp.get(position));
                startActivity(n);
            }
        });
    }
}
