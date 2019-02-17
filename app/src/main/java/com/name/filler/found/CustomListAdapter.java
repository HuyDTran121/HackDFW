package com.name.filler.found;

import android.app.Activity;
import android.graphics.Bitmap;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import static com.name.filler.found.MainActivity.items;

public class CustomListAdapter extends ArrayAdapter {

    public CustomListAdapter(Activity context, ArrayList<Data> items) {

        super(context, R.layout.list_adapter, items);

        this.context = context;

    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.list_adapter, null, true);

        //this code gets references to objects in the listview_row.xml file
        TextView nameTextField = (TextView) rowView.findViewById(R.id.nameTextViewID);
        TextView infoTextField = (TextView) rowView.findViewById(R.id.infoTextViewID);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.imageView);
        //this code sets the values of the objects to values from the arrays
        nameTextField.setText(items.get(position).room);
        imageView.setImageBitmap(items.get(position).image);
        infoTextField.setText(MainActivity.currentItem);

        return rowView;

    }

    //to reference the Activity
    private final Activity context;
}