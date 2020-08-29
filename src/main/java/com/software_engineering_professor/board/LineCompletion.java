package com.software_engineering_professor.board;

import com.software_engineering_professor.geom.Point;

import java.util.Collection;
import java.util.Objects;

public class LineCompletion {
    // All points must belong to the same line.
    public boolean isComplete(int width, Collection<Point> points) {
        Objects.requireNonNull(points);

        int sumForLineCompletion = (width - 1) * width / 2;

        return points.stream().mapToInt(p -> p.x).sum() == sumForLineCompletion;
    }
}
