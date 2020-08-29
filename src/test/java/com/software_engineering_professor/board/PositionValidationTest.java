package com.software_engineering_professor.board;

import com.software_engineering_professor.geom.Point;
import com.software_engineering_professor.piece.Piece;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

class PositionValidationTest {
    private PositionValidation positionValidation;
    private Board board;
    private Piece piece;
    private Collection<Point> points;

    @BeforeEach
    public void setUp() {
        board = spy(new BoardImpl(20, 20));
        positionValidation = new PositionValidation(board);
        piece = mock(Piece.class);
        points = new ArrayList<>();
    }


    @Test
    public void givenAPointOutsideUpperLeftPoint_thenNotValid() {
        Point upperLeft = board.getUpperLeftPoint();
        Point outside = new Point(upperLeft.x - 1, upperLeft.y - 1);

        assertFalse(positionValidation.isValid(piece, Arrays.asList(upperLeft, outside)));
    }

    @Test
    public void givenAPointOutsideBoardDimensions_thenNotValid() {
        assertFalse(positionValidation.isValid(piece, Collections.singletonList(new Point(25, 25))));
    }

    @Test
    public void givenAPointInTheBorder_thenNotValid() {
        assertFalse(positionValidation.isValid(piece, Collections.singletonList(new Point(20, 20))));
    }

    @Test
    public void givenAllThePointsWithinBoard_thenValid() {
        assertTrue(positionValidation.isValid(piece, Collections.singletonList(new Point(19, 19))));
    }

    @Test
    public void givenAPointIsOccupied_thenNotValid() {
        Collection<Point> newPoints = Arrays.asList(new Point(5, 5),
                                                    new Point(6, 6),
                                                    new Point(7, 7));

        doReturn(Collections.singletonList(new Point(5, 5))).when(board).getOccupiedPoints(piece);
        assertFalse(positionValidation.isValid(piece, newPoints));
    }

    @Test
    public void givenAllPointAreOccupied_thenNotValid() {
        Collection<Point> newPoints = Arrays.asList(new Point(5, 5),
                                                    new Point(6, 6),
                                                    new Point(7, 7));

        doReturn(newPoints).when(board).getOccupiedPoints(piece);
        assertFalse(positionValidation.isValid(piece, newPoints));
    }

    @Test
    public void givenAllPointsFree_thenValid() {
        Collection<Point> newPoints = Arrays.asList(new Point(5, 5),
                                                    new Point(6, 6),
                                                    new Point(7, 7));

        doReturn(new ArrayList<>()).when(board).getOccupiedPoints(piece);
        assertTrue(positionValidation.isValid(piece, newPoints));
    }

    @Test
    public void givenAllPointsFreeV2_thenValid() {
        Collection<Point> newPoints = Arrays.asList(new Point(5, 5),
                                                    new Point(6, 6),
                                                    new Point(7, 7));

        doReturn(Arrays.asList(new Point(5, 6))).when(board).getOccupiedPoints(piece);
        assertTrue(positionValidation.isValid(piece, newPoints));
    }
}