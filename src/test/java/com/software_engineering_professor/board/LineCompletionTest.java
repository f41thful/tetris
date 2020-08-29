package com.software_engineering_professor.board;

import com.software_engineering_professor.geom.Point;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LineCompletionTest {
    private LineCompletion lineCompletion;

    @BeforeEach
    public void setUp() {
        lineCompletion = new LineCompletion();
    }

    @Test
    public void givenNotCompleted_thenNotCompleted() {
        Collection<Point> points = getPoints(0, 0, 1, 3);
        assertFalse(lineCompletion.isComplete(4, points));
    }

    @Test
    public void givenEmpty_thenNotCompleted() {
        assertFalse(lineCompletion.isComplete(4, new ArrayList<>()));
    }

    @Test
    public void givenComplete_thenCompleted() {
        Collection<Point> points = getPoints(0, 0, 1, 2, 3);
        assertTrue(lineCompletion.isComplete(4, points));
    }

    @Test
    public void givenNotComplete_thenNotCompleted() {
        Collection<Point> points = getPoints(0, 1, 2, 3);
        assertFalse(lineCompletion.isComplete(4, points));
    }

    private Collection<Point> getPoints(int y, Integer... xs) {
        Collection<Point> points = new ArrayList<>();
        for(Integer x : xs) {
            points.add(new Point(x, y));
        }

        return points;
    }
}