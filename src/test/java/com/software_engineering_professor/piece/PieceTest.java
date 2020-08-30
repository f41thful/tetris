package com.software_engineering_professor.piece;

import com.software_engineering_professor.geom.Point;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PieceTest {
    private Piece piece;
    private Collection<Point> localPoints;

    @BeforeEach
    public void setUp() {
        piece = PieceBuilder.create(0, new Point(2, 2))
                            .add("x  ")
                            .add("x  ")
                            .add("xx ").build();

        localPoints = piece.getLocalPoints();
    }

    @Test
    public void givenPieceIsNotInLine_thenDeletesNothing() {
        piece.deleteGlobalLine(0);

        assertEquals(localPoints, piece.getLocalPoints());
    }

    @Test
    public void givenPieceIsNotInLineV2_thenDeletesNothing() {
        piece.deleteGlobalLine(5);

        assertEquals(localPoints, piece.getLocalPoints());
    }

    @Test
    public void givenOnePointIsInTheLine_thenDeletesOnlyThatPoint() {
        piece.deleteGlobalLine(2);

        Collection<Point> actualPoints = piece.getLocalPoints();
        assertEquals(3, actualPoints.size());
        for(Point p : actualPoints) {
            assertNotEquals(0, p.y);
            assertTrue(localPoints.contains(p));
        }
    }

    @Test
    public void givenTwoPointsInTheLine_thenDeletesOnlyThosePoints() {
        piece.deleteGlobalLine(4);

        Collection<Point> actualPoints = piece.getLocalPoints();
        assertEquals(2, actualPoints.size());
        for(Point p : actualPoints) {
            assertNotEquals(2, p.y);
            assertTrue(localPoints.contains(p));
        }
    }
}