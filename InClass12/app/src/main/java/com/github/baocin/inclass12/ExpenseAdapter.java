package com.github.baocin.inclass12;
////InClass 12
////Group 18
////4-18-16
////Praveenkumar Sangalad
////Michael Pedersen
////Gabriel Lima
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by gbl on 4/11/2016.
 */
public class ExpenseAdapter extends ArrayAdapter<Expense>{

    Context context;
    ArrayList<Expense> objects;


    public ExpenseAdapter(Context context, ArrayList<Expense> objects) {
        super(context, R.layout.expense_item, objects);
        this.context = context;
        this.objects = objects;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.expense_item, parent,false);
            holder = new ViewHolder();
            holder.expenseName = (TextView) convertView.findViewById(R.id.expenseName);
            holder.expensePrice = (TextView) convertView.findViewById(R.id.expenseValue);
            convertView.setTag(holder);
        }
        holder = (ViewHolder) convertView.getTag();
        TextView colorName = holder.expenseName;
        TextView colorHex = holder.expensePrice;
        if (objects.get(position) == null){
            return convertView;
        }
        colorName.setText(objects.get(position).getName());
        colorHex.setText("$" + objects.get(position).getAmount());  //¥
        return convertView;
    }

    static class ViewHolder{
        TextView expenseName;
        TextView expensePrice;
    }

}
