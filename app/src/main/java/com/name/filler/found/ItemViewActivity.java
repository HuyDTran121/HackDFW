package com.name.filler.found;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class ItemViewActivity extends AppCompatActivity {
    private static Item item;

    public static Item getItem() {
        return item;
    }

    public static void setItem(Item i) {
        item = i;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_view);
        ImageView image =(ImageView) findViewById(R.id.ImageView);
        Bitmap bmp = item.getImage();
        image.setImageBitmap(Bitmap.createScaledBitmap(bmp,image.getWidth(),image.getHeight(),false));

    }
}
