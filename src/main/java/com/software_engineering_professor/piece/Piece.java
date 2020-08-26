package com.software_engineering_professor.piece;

import com.software_engineering_professor.geom.Point;
import com.software_engineering_professor.util.CollectionUtil;

import java.util.Collection;
import java.util.OptionalInt;

public class Piece {
    private int type;
    private Point position;
    private int width;
    private int height;
    private Collection<Point> occupiedPoints;

    public Piece(int type, Point position, Collection<Point> occupiedPoints) {
        this.type = type;
        this.position = position.clone();
        this.occupiedPoints = CollectionUtil.deepCopy(occupiedPoints);
        calculateDimensions();
    }

    private void calculateDimensions() {
        OptionalInt optionalWidth = occupiedPoints.stream().mapToInt(p -> p.x).max();
        if (!optionalWidth.isPresent()) {
            throw new IllegalStateException("Couldn't get the max width for points " + occupiedPoints);
        }

        OptionalInt optionalHeight = occupiedPoints.stream().mapToInt(p -> p.y).max();
        if (!optionalHeight.isPresent()) {
            throw new IllegalStateException("Couldn't get the max height for points " + occupiedPoints);
        }
    }

    public int getType() {
        return type;
    }

    public Collection<Point> getGlobalPoints() {
        Collection<Point> points = CollectionUtil.deepCopy(occupiedPoints);
        for(Point p : points) {
            p.x += position.x;
            p.y += position.y;
        }

        return points;
    }
}
