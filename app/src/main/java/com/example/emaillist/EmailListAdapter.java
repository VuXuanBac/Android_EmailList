package com.example.emaillist;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class EmailListAdapter extends BaseAdapter {

    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    int[] sender_image_colors;
    LayoutInflater inflater;
    List<EmailInfo> items;
    int layout;

    public EmailListAdapter(@NonNull Context context, int resource, @NonNull List<EmailInfo> objects){
        sender_image_colors = new int[]{
               Color.rgb(0, 255, 0), // green
               Color.rgb(255, 170, 170),
               Color.rgb(70, 233, 235),
               Color.rgb(156, 39, 176),
               Color.rgb(255, 87, 34),
                Color.rgb(139, 195, 74)
        };
        inflater = LayoutInflater.from(context);
        layout = resource;
        items = objects;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @NonNull
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View layout_view = inflater.inflate(layout, null);
        EmailInfo selected_item = items.get(i);

        TextView textImage = layout_view.findViewById(R.id.textImage);
        TextView textTitle = layout_view.findViewById(R.id.textTitle);
        TextView textDate = layout_view.findViewById(R.id.textDate);
        TextView textSender = layout_view.findViewById(R.id.textSender);
        TextView textContent = layout_view.findViewById(R.id.textContent);
        ImageView imageStar = layout_view.findViewById(R.id.imageStar);

        textTitle.setText(selected_item.getTitle());
        textContent.setText(selected_item.getContent());

        textDate.setText(dateFormat.format(selected_item.getSendDate()));

        String sender = selected_item.getSender();
        textSender.setText(sender);
        textImage.setText(Character.toString(sender.charAt(0)));
        textImage.setBackgroundTintList(ColorStateList.valueOf(sender_image_colors[i % sender_image_colors.length]));

        if(selected_item.isStar()){
            imageStar.setImageResource(R.drawable.ic_solid_star_24);
        }
        else{
            imageStar.setImageResource(R.drawable.ic_empty_star_24);
        }
        return layout_view;
    }
}
