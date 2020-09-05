package com.software_engineering_professor.graphics.lanterna.drawer;

import com.software_engineering_professor.geom.Point;

import java.util.Collection;
import java.util.Objects;

public abstract class Drawer {
    private Point origin;

    public Drawer(Point origin) {
        Objects.requireNonNull(origin);
        this.origin = origin;
    }

    protected Collection<Point> transformToOrigin(Collection<Point> points) {
        points.forEach(p -> {p.x += origin.x; p.y+= origin.y;});
        return points;
    }
}
