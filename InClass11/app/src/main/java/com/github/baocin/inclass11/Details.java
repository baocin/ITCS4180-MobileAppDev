package com.github.baocin.inclass11;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;

public class Details extends AppCompatActivity {
    Expense e;
    Firebase ref2;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.deleteExpenses:
                Log.d("sdlkjfds", "lkdsjf" + e.toString());
                Log.d("key", "key" + e.getKey());
                ref2.child(ref2.getAuth().getUid()).child(e.getKey()).setValue(null);
                Toast.makeText(getApplicationContext(), "DELETED YOUR EXPENSE!", Toast.LENGTH_SHORT).show();

                finish();
                return true;
        };
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);


        Firebase.setAndroidContext(getApplicationContext());
        final Firebase ref = new Firebase("https://inclass11.firebaseio.com/expenses");
            ref2 = ref;


        Bundle extras = getIntent().getExtras();
        e = (Expense) extras.get("exp");

        TextView name = (TextView) findViewById(R.id.name);
        TextView cat = (TextView) findViewById(R.id.cat);
        TextView amount = (TextView) findViewById(R.id.amount);
        TextView date = (TextView) findViewById(R.id.date);

        name.setText(e.getName());
        cat.setText(e.getCategory());
        amount.setText("$" + e.getAmount());
        date.setText(e.getDate());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

}
