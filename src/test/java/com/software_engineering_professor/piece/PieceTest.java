package com.software_engineering_professor.piece;

import com.software_engineering_professor.board.Board;
import com.software_engineering_professor.board.PositionValidation;
import com.software_engineering_professor.geom.Point;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class PieceTest {
    private Piece piece;
    private Collection<Point> points;

    @BeforeEach
    public void setUp() {
        piece = new Piece(0, new Point(0, 0), getPoints(), new PositionValidation(mock(Board.class)));
        points = getPoints();
    }

    @Test
    public void givenPointIsNotInThePiece_thenDoNothing() {
        piece.delete(Collections.singletonList(new Point(7, 7)));
        assertEquals(points, piece.getLocalPoints());
    }

    @Test
    public void givenPointIsInThePiece_thenRemoveIt() {
        piece.delete(Collections.singletonList(new Point(0, 0)));
        points.remove(new Point(0, 0));

        assertEquals(points, piece.getLocalPoints());
    }

    @Test
    public void givenPointsAreInThePiece_thenRemoveThem() {
        piece.delete(Arrays.asList(new Point(0, 0), new Point(0, 1), new Point(1, 2), new Point(7, 7)));
        points.remove(new Point(0, 0));
        points.remove(new Point(0, 1));
        points.remove(new Point(1, 2));

        assertEquals(points, piece.getLocalPoints());
    }

    private Collection<Point> getPoints() {
        return
                new ArrayList<>(Arrays.asList(new Point(0, 0),
                        new Point(0, 1),
                        new Point(0, 2),
                        new Point(1, 2)
                ));
    }
}