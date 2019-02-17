package com.name.filler.found;
import android.graphics.Bitmap;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Item implements Serializable {
    private String name;
    private byte[] image;
    private Point upperLeft;
    private Point lowerRight;
    private int id;

    public Item() {
        name =null;
        image =null;
        upperLeft= null;
        lowerRight = null;
        id= 0;
    }

    public Item(String name, byte[] image, Point upperLeft, Point lowerRight, int id) {
        this.name = name;
        this.image = image;
        this.upperLeft = upperLeft;
        this.lowerRight = lowerRight;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Point getUpperLeft() {
        return upperLeft;
    }

    public void setUpperLeft(Point upperLeft) {
        this.upperLeft = upperLeft;
    }

    public Point getLowerRight() {
        return lowerRight;
    }

    public void setLowerRight(Point lowerRight) {
        this.lowerRight = lowerRight;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

//    private void writeObject(ObjectOutputStream out) throws IOException{
//        out.writeUTF(name);
//        out.writeObject(image);
//        out.writeObject(upperLeft);
//        out.writeObject(lowerRight);
//        out.write(id);
//    }
//    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException{
//        name = in.readUTF();
//        image = (Bitmap)in.readObject();
//        upperLeft = (Point)in.readObject();
//        lowerRight = (Point)in.readObject();
//        id = in.readInt();
//    }
}
