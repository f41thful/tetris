package com.software_engineering_professor.geom;

import com.software_engineering_professor.util.Cloneable;

public class Point implements Cloneable<Point> {
    public int x;
    public int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Point clone() {
        return new Point(this.x, this.y);
    }
}
