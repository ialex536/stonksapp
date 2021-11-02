package com.example.stonks;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import java.util.ArrayList;
import android.content.Context;
import android.widget.Button;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ListAdapter extends ArrayAdapter<Course> {

    public ListAdapter(Context context, ArrayList<Course> courseArrayList) {

        super(context, R.layout.list_item, courseArrayList);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Course course = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        Button courseButton = convertView.findViewById(R.id.list_item_button);
        TextView courseName = convertView.findViewById(R.id.list_item_title);
        TextView courseDescription = convertView.findViewById(R.id.list_item_message);

        courseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getContext(),InfoActivity.class);
                i.putExtra("courseNum", course.courseNum);
                getContext().startActivity(i);
            }
        });

        courseButton.setText("Go");
        courseName.setText(course.title);
        courseDescription.setText(course.description);

        return convertView;
    }
}