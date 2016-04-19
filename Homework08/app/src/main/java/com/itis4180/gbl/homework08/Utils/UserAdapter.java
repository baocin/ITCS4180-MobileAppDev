package com.itis4180.gbl.homework08.Utils;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.itis4180.gbl.homework08.R;

import java.util.ArrayList;

/**
 * Created by gbl on 4/15/2016.
 */
public class UserAdapter extends ArrayAdapter<User> {

    private ViewHolder holder;
    private Context context;
    private ArrayList<User> objects;
    private int pos;

    public UserAdapter(Context context, ArrayList<User> objects) {
        super(context, R.layout.contact_layout, objects);

        this.context = context;
        this.objects = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        pos = position;

        if (convertView == null) {

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.contact_layout, parent, false);

            holder              = new ViewHolder();
            holder.thumb        = (ImageView) convertView.findViewById(R.id.contactThumb);
            holder.name         = (TextView) convertView.findViewById(R.id.contactName);
            holder.unread       = (ImageView) convertView.findViewById(R.id.unreadMsgIcon);
            holder.callButton   = (ImageButton) convertView.findViewById(R.id.callButton);

            convertView.setTag(holder);
        }

        holder = (ViewHolder) convertView.getTag();

        ImageView thumb         = holder.thumb;
        TextView name           = holder.name;
        ImageView unread        = holder.unread;
        ImageButton callButton  = holder.callButton;

        byte[] imageAsBytes = Base64.decode(objects.get(position).getPicture().getBytes(), Base64.DEFAULT);     // Decoding Image back to ByteArray
        thumb.setImageBitmap(BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length));              // Adding the image as a Bitmap

        name.setText(objects.get(position).getName());
        unread.setImageResource(R.drawable.redbubbleclipart);
        callButton.setImageResource(R.drawable.phoneiconhi);

        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + objects.get(pos).getPhoneNumber()));
                if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(context, "Enable Call Permission", Toast.LENGTH_SHORT).show();
                    return;
                }
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getContext().startActivity(intent);
            }
        });

        return convertView;
    }

    static class ViewHolder{
        ImageView thumb;
        TextView name;
        ImageView unread;
        ImageButton callButton;
    }
}