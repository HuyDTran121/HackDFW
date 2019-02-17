package com.name.filler.found;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;

public class Data{
    public String room;
    public Bitmap image;
    public double x1,x2,y1,y2;
    public Canvas rectangle;
    Data(String room,Bitmap image,double x1,double x2,double y1,double y2){
        this.room=room;
        //this.image=image;
        this.x1=x1*image.getWidth();
        this.x2=x2*image.getWidth();
        this.y1=y1*image.getHeight();
        this.y2=y2*image.getHeight();
        System.out.println("DATA "+this.x1+" "+this.x2+" "+this.y1+" "+this.y2);
        //Bitmap bmOverlay = Bitmap.createBitmap(image.getWidth(), image.getHeight(), image.getConfig());
        Bitmap bmCopy = image.copy(Bitmap.Config.ARGB_8888,true);
        this.image=bmCopy;
        rectangle = new Canvas(bmCopy);
        rectangle.setBitmap(bmCopy);
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        //rectangle.setBitmap(image);
        //rectangle.setBitmap(image);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);
        rectangle.drawRect((float)this.x1,(float)this.y1,(float)(this.x2-this.x1),(float)(this.y2-this.y1),paint);

        rectangle.save();
        //this.image=rectangle;*/
    }

    public boolean equals(Object d2){
        Data d= (Data)d2;
        return image.equals(((Data) d2).image);
    }
}