package com.software_engineering_professor.board;

import com.software_engineering_professor.geom.Point;

import java.util.Collection;
import java.util.Objects;

public class LineCompletion {
    // All points must belong to the same line.
    public boolean isComplete(int width, Collection<Point> points) {
        Objects.requireNonNull(points);

        return points.size() == width;
    }
}
