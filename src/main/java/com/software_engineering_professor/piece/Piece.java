package com.software_engineering_professor.piece;

import com.software_engineering_professor.board.PositionValidation;
import com.software_engineering_professor.geom.Point;
import com.software_engineering_professor.util.CollectionUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.OptionalInt;
import java.util.stream.Collectors;

public class Piece {
    private int type;
    private Point position;
    private int width;
    private int height;
    private Collection<Point> occupiedPoints;
    private PositionValidation positionValidation;

    public Piece(int type, Point position, Collection<Point> occupiedPoints, PositionValidation positionValidation) {
        if(type < 0) {
            throw new IllegalArgumentException("type must be >= 0, it was " + type);
        }

        validatePoint(position, "Position must be non null and coordinates must both be >= 0.");

        Objects.requireNonNull(occupiedPoints);

        if(occupiedPoints.isEmpty()) {
            throw new IllegalArgumentException("occupied points cannot be empty.");
        }

        for(Point p : occupiedPoints) {
            validatePoint(p, "Occupied points must be non null and coordinates must both be >= 0.");
        }

        this.type = type;
        this.position = position.clone();
        this.occupiedPoints = CollectionUtil.deepCopy(occupiedPoints);
        calculateDimensions();

        Objects.requireNonNull(positionValidation);
        this.positionValidation = positionValidation;
    }

    private void calculateDimensions() {
        OptionalInt optionalWidth = occupiedPoints.stream().mapToInt(p -> p.x).max();
        if (!optionalWidth.isPresent()) {
            throw new IllegalStateException("Couldn't get the max width for points " + occupiedPoints);
        }

        width = optionalWidth.getAsInt();

        OptionalInt optionalHeight = occupiedPoints.stream().mapToInt(p -> p.y).max();
        if (!optionalHeight.isPresent()) {
            throw new IllegalStateException("Couldn't get the max height for points " + occupiedPoints);
        }

        height = optionalHeight.getAsInt();
    }

    public int getType() {
        return type;
    }

    public Collection<Point> getGlobalPoints() {
       return getGlobalPoints(position, occupiedPoints);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void moveDown(int numPos) {
        if(numPos < 0) {
            throw new IllegalArgumentException("numPost must be natural.");
        }

        Point newPosition = new Point(position.x, position.y + numPos);
        if(positionValidation.isValid(this, getGlobalPoints(newPosition, occupiedPoints))) {
            position.y += numPos;
        }
    }

    public void moveHorizontal(int numPos) {
        Point newPosition = new Point(position.x + numPos, position.y);
        if(positionValidation.isValid(this, getGlobalPoints(newPosition, occupiedPoints))) {
            position.x += numPos;
        }
    }

    public void rotateLeft() {
        Collection<Point> newPoints = new ArrayList<>();
        for(Point p : occupiedPoints) {
            newPoints.add(new Point(p.y, height - 1 - p.x));
        }

        if(positionValidation.isValid(this, getGlobalPoints(position, newPoints))) {
            occupiedPoints = newPoints;
        }
    }

    public void delete(Collection<Point> points) {
        Objects.requireNonNull(points);
        Collection<Point> toBeRemoved;

        toBeRemoved = occupiedPoints.stream().filter(points::contains).collect(Collectors.toList());
        toBeRemoved.forEach(occupiedPoints::remove);
    }

    public Collection<Point> getLocalPoints() {
        return CollectionUtil.deepCopy(occupiedPoints);
    }

    private void validatePoint(Point p, String msg) {
        Objects.requireNonNull(p);
        if(p.x < 0 || p.y < 0) {
            throw new IllegalArgumentException(msg);
        }
    }

    private Collection<Point> getGlobalPoints(Point position, Collection<Point> occupiedPoints) {
        Collection<Point> points = CollectionUtil.deepCopy(occupiedPoints);
        for(Point p : points) {
            p.x += position.x;
            p.y += position.y;
        }

        return points;
    }
}
