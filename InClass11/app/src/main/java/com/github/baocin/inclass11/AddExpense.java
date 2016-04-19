package com.github.baocin.inclass11;

////InClass 11
////Group 18
////4-11-16
////Praveenkumar Sangalad
////Michael Pedersen
////Gabriel Lima
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.firebase.client.Firebase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class AddExpense extends AppCompatActivity {

    String category;

    private EditText dateTextField;
    EditText expenseTextField;
    EditText amountTextField;

    Spinner categorySpinner;
    ArrayAdapter<CharSequence> categoryArrayAdapter;

    ImageButton calendarButton;

    Button addExpenseButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);


        Firebase.setAndroidContext(getApplicationContext());
        final Firebase ref = new Firebase("https://inclass11.firebaseio.com/expenses");


        expenseTextField    = (EditText) findViewById(R.id.expenseTextField);
        amountTextField     = (EditText) findViewById(R.id.amountTextField);

        /** Spinner **/

        categorySpinner         = (Spinner) findViewById(R.id.categorySpinnerrr);
        categoryArrayAdapter    = ArrayAdapter.createFromResource(this, R.array.categories_array, android.R.layout.simple_spinner_item);

        categoryArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(categoryArrayAdapter);
        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                category = parent.getItemAtPosition(pos).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                category = parent.getItemAtPosition(0).toString();
            }
        });

        /** Calendar Button & Date Picker **/
        dateTextField = (EditText) findViewById(R.id.dateTextField);

        calendarButton = (ImageButton) findViewById(R.id.calendarButtonnn);
        calendarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new DatePickerFragment();
                newFragment.show(getSupportFragmentManager(), "datePicker");
            }
        });

        /** Add Button **/
        addExpenseButton = (Button) findViewById(R.id.addExpenseButton);
        addExpenseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = expenseTextField.getText().toString();
                String amount = amountTextField.getText().toString();
                String date = dateTextField.getText().toString();

                if (name == null) {
                    Toast.makeText(AddExpense.this, "Name Field Empty", Toast.LENGTH_SHORT).show();
                }

                if (amount == null) {
                    Toast.makeText(AddExpense.this, "Amount Field Empty", Toast.LENGTH_SHORT).show();
                }

                if (date == null) {
                    Toast.makeText(AddExpense.this, "Date Field Empty", Toast.LENGTH_SHORT).show();
                }

                if (name != null && amount != null && date != null){

                    /*
                    Log.d("NAME:", name);

                    Log.d("Category:", category);
                    Log.d("Amount:", amount);
                    Log.d("Date:", date);
*/
                    Expense e = new Expense(amount, category, date, name, ref.getAuth().getUid());

                    HashMap m = new HashMap<String, String>();
                    m.put("amount", amount);
                    m.put("date", date);
                    m.put("category", category);
                    m.put("name", name);
                    m.put("user", ref.getAuth().getUid());
                    ref.child(ref.getAuth().getUid()).push().setValue(m);

                    Toast.makeText(getApplicationContext(), "ADDED YOUR EXPENSE!", Toast.LENGTH_SHORT).show();
                    finish();
                }

            }
        });
    }

    public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {

            SimpleDateFormat month_date = new SimpleDateFormat("MMM");
            String month_name = month_date.format(month);

            dateTextField.setText(month_name + " " + day + " " + year);
        }
    }
}
