package com.cse110w240t1.systemmoniter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by fanfan on 2/7/16.
 */

class CustomAdapter extends ArrayAdapter<String> {

    public CustomAdapter(Context context, String[] information) {
        super(context, R.layout.custom_row, information);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater customInflater = LayoutInflater.from(getContext());
        View customView = customInflater.inflate(R.layout.custom_row, parent, false);

        String cellName = getItem(position);
        TextView title = (TextView) customView.findViewById(R.id.info_title);
        TextView content = (TextView) customView.findViewById(R.id.info_content);
        ImageView image = (ImageView) customView.findViewById(R.id.info_icon);

        title.setText(cellName);
        content.setText("Hello World");
        image.setImageResource(R.drawable.ram_icon);

        return customView;
    }
}
