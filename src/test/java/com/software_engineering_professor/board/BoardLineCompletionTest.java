package com.software_engineering_professor.board;

import com.software_engineering_professor.geom.Point;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

class BoardLineCompletionTest {
    private static final int BOARD_SIZE = 4;
    private Board board;
    private BoardLineCompletion boardLineCompletion;
    private Collection<Point> points;

    @BeforeEach
    public void setUp() {
        board = mock(Board.class);
        boardLineCompletion = new BoardLineCompletion();
        points = new ArrayList<>();
    }

    @Test
    public void givenNoLinesIsCompleted_thenReturnNoLine() {
        points = getPoints(0, 0, 1, 2);
        points.addAll(getPoints(1, 1, 2));

        doReturn(points).when(board).getOccupiedPoints(null);
        doReturn(BOARD_SIZE).when(board).getWidth();
        assertEquals(0, boardLineCompletion.getCompletedLines(board).size());
    }

    @Test
    public void givenTheFirstLineIsCompleted_thenReturnThatLine() {
        points = getPoints(0, 0, 1, 2, 3);
        points.addAll(getPoints(1, 1, 2));

        doReturn(points).when(board).getOccupiedPoints(null);
        doReturn(BOARD_SIZE).when(board).getWidth();

        Collection<Integer> lines = boardLineCompletion.getCompletedLines(board);
        assertEquals(1, lines.size());
        assertTrue(lines.contains(0));
    }

    @Test
    public void givenTheSecondLineIsCompleted_thenReturnThatLine() {
        points = getPoints(0, 0, 2, 3);
        points.addAll(getPoints(1, 0, 1, 2, 3));
        points.addAll(getPoints(2, 1, 2, 3));

        doReturn(points).when(board).getOccupiedPoints(null);
        doReturn(BOARD_SIZE).when(board).getWidth();

        Collection<Integer> lines = boardLineCompletion.getCompletedLines(board);
        assertEquals(1, lines.size());
        assertTrue(lines.contains(1));
    }

    @Test
    public void givenThe2LinesAreCompleted_thenReturnBothLines() {
        points = getPoints(0, 0, 1, 2, 3);
        points.addAll(getPoints(1, 1, 2, 3));
        points.addAll(getPoints(2, 0, 1, 2, 3));

        doReturn(points).when(board).getOccupiedPoints(null);
        doReturn(BOARD_SIZE).when(board).getWidth();

        Collection<Integer> lines = boardLineCompletion.getCompletedLines(board);
        assertEquals(2, lines.size());
        assertTrue(lines.contains(0));
        assertTrue(lines.contains(2));
    }

    @Test
    public void givenAllLinesAreCompleted_thenReturnAllLines() {
        points = getPoints(0, 0, 1, 2, 3);
        points.addAll(getPoints(1, 0, 1, 2, 3));
        points.addAll(getPoints(2, 0, 1, 2, 3));

        doReturn(points).when(board).getOccupiedPoints(null);
        doReturn(BOARD_SIZE).when(board).getWidth();

        Collection<Integer> lines = boardLineCompletion.getCompletedLines(board);
        assertEquals(3, lines.size());
        assertTrue(lines.contains(0));
        assertTrue(lines.contains(1));
        assertTrue(lines.contains(2));
    }

    private Collection<Point> getPoints(int y, Integer... xs) {
        Collection<Point> points = new ArrayList<>();
        for(Integer x : xs) {
            points.add(new Point(x, y));
        }

        return points;
    }
}