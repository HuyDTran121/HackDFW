package com.name.filler.found;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class ItemViewActivity extends AppCompatActivity {
    private static Item item;
    private static ImageView image;
    public Item getItem() {
        return item;
    }

    public static void setItem(Item i) {
        item = i;
        byte[] byteArray = item.getImage();
        Bitmap bmp = BitmapFactory.decodeByteArray(byteArray,0,byteArray.length);
        image.setImageBitmap(Bitmap.createScaledBitmap(bmp,bmp.getWidth(),bmp.getHeight(),false));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_view);
        ImageView image =(ImageView) findViewById(R.id.ImageView);
        if(item != null)
            setItem(new Item());
    }
}
