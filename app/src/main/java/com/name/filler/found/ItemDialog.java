package com.name.filler.found;

import android.app.Activity;
import android.app.*;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.name.filler.found.R;

import javax.xml.transform.Templates;

public class ItemDialog extends Dialog implements View.OnClickListener {
    public Activity c;
    public Dialog d;
    public TextView item;
    public TextView location;
    public ImageView image;

    public ItemDialog(Activity a) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.item_dialog);
        item = (TextView)findViewById(R.id.item);
        location = (TextView)findViewById(R.id.location);
        image = (ImageView)findViewById(R.id.ImageView);




    }

    @Override
    public void onClick(View v) {
        c.finish();
        dismiss();
    }

    public void setImage(Bitmap b){
        image.setImageBitmap(b);
    }
}
