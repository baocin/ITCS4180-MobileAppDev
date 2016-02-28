package com.example.gbl.homework4;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

/**
 * Created by gbl on 2/27/2016.
 */
public class CustomButton extends Button {

    Movie mv;
    Context context;

    public CustomButton(final Context context, Movie mv) {
        super(context);

        this.mv = mv;
        this.context = context;

        this.setText(mv.getTitle());
        this.setTextColor(Color.BLACK);
        this.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        this.setBackgroundColor(Color.TRANSPARENT);
        this.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d("Testing", CustomButton.this.mv.getTitle());


                Intent intent = new Intent(CustomButton.this.context, MovieDetailsActivity.class);
                intent.putExtra("SELECTED_MOVIE", CustomButton.this.mv);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
                CustomButton.this.context.startActivity(intent);
            }
        });


    }
}
