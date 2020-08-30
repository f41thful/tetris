package com.software_engineering_professor.board;

import com.software_engineering_professor.geom.Point;
import com.software_engineering_professor.piece.Piece;

import java.util.Collection;
import java.util.List;

public interface Board {
    void addPiece(Piece p);

    Point getUpperLeftPoint();
    int getWidth();
    int getHeight();

    // get occupied points without taking into account piece p.
    Collection<Point> getOccupiedPoints(Piece p);
    List<Piece> getPiecesAboveOrInLineOrderedByHeightDesc(int line);
}
