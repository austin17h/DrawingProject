package com.example.a1474672.drawingproject;

import android.graphics.Point;

public class PointStorage {
    private int color;
    private Point point;
    private int size;

    public PointStorage()
    {}

    public PointStorage(Point x, int y, int z)
    {
        color = y;
        point = x;
        size = z;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
