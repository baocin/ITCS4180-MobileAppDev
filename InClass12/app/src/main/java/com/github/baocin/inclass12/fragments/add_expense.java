package com.github.baocin.inclass12.fragments;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.github.baocin.inclass12.Expense;
import com.github.baocin.inclass12.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;


public class add_expense extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_expense, container, false);
    }




    String category;

    private EditText dateTextField;
    EditText expenseTextField;
    EditText amountTextField;

    Spinner categorySpinner;
    ArrayAdapter<CharSequence> categoryArrayAdapter;

    ImageButton calendarButton;

    Button addExpenseButton;


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    Firebase.setAndroidContext(getActivity().getApplicationContext());
        final Firebase ref = new Firebase("https://inclass012.firebaseio.com/expenses");

        expenseTextField    = (EditText) getActivity().findViewById(R.id.expenseTextField);
        amountTextField     = (EditText) getActivity().findViewById(R.id.amountTextField);

        /** Spinner **/

        categorySpinner         = (Spinner) getActivity().findViewById(R.id.categorySpinnerrr);
        categoryArrayAdapter    = ArrayAdapter.createFromResource(getActivity(), R.array.categories_array, android.R.layout.simple_spinner_item);

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
        dateTextField = (EditText) getActivity().findViewById(R.id.dateTextField);

        calendarButton = (ImageButton) getActivity().findViewById(R.id.calendarButtonnn);
        calendarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new DatePickerFragment();
                newFragment.show(getFragmentManager(), "klsdjf");
                //newFragment.show(getSupportFragmentManager(), "datePicker");
            }
        });

        /** Add Button **/
        addExpenseButton = (Button) getActivity().findViewById(R.id.addExpenseButton);
        addExpenseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = expenseTextField.getText().toString();
                String amount = amountTextField.getText().toString();
                String date = dateTextField.getText().toString();

                if (name == null || name.length() == 0) {
                    Toast.makeText(getActivity().getApplicationContext(), "Name Field Empty", Toast.LENGTH_SHORT).show();
                }

                if (amount == null || amount.length() == 0) {
                    Toast.makeText(getActivity().getApplicationContext(), "Amount Field Empty", Toast.LENGTH_SHORT).show();
                }

                if (date == null || date.length() == 0) {
                    Toast.makeText(getActivity().getApplicationContext(), "Date Field Empty", Toast.LENGTH_SHORT).show();
                }

                if (name != null && amount != null && date != null && name.length() != 0 && amount.length() != 0 && date.length() != 0){

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

                    Toast.makeText(getActivity().getApplicationContext(), "ADDED YOUR EXPENSE!", Toast.LENGTH_SHORT).show();
                    getFragmentManager().popBackStack();



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
            Log.d("Date", year + "," + month + "," + day);
            Date pickedDate = new Date(year,month,day);
            SimpleDateFormat formatter = new SimpleDateFormat("MMM");
            String month_name = formatter.format(pickedDate);

            dateTextField.setText(month_name + " " + day + " " + year);
        }
    }
}
