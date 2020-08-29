package com.software_engineering_professor.board;

import com.software_engineering_professor.geom.Point;
import com.software_engineering_professor.piece.Piece;

import java.util.Collection;
import java.util.Objects;
import java.util.function.Function;

public class PositionValidation {
    private Board board;

    public PositionValidation(Board board) {
        this.board = board;
    }

    public boolean isValid(Piece p, Collection<Point> points) {
        Objects.requireNonNull(p);
        Objects.requireNonNull(points);
        return arePointsFree(p, points) && areWithinTheBoard(points);
    }

    private boolean areWithinTheBoard(Collection<Point> points) {
        Point upperLeftPoint = board.getUpperLeftPoint();
        return inRangeClosed(points, p -> p.x, upperLeftPoint.x, board.getWidth() - 1)
               &&
               inRangeClosed(points, p -> p.y, upperLeftPoint.y, board.getHeight() - 1);
    }

    private boolean inRangeClosed(Collection<Point> points, Function<Point, Integer> mapper, int begin, int end) {
        return
            points.stream()
                   .map(mapper)
                   .noneMatch(v -> v < begin || v > end);
    }

    private boolean arePointsFree(Piece piece, Collection<Point> points) {
        Collection<Point> boardPoints = board.getOccupiedPoints(piece);

        for(Point p : points) {
            if(boardPoints.contains(p)) {
                return false;
            }
        }

        return true;
    }
}
