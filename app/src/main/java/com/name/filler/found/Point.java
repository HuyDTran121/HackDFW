package com.name.filler.found;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Point implements Serializable {
    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.write(x);
        out.write(y);
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        x = in.readInt();
        y = in.readInt();
    }
}
