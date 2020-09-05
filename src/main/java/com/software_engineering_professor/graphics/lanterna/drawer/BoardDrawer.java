package com.software_engineering_professor.graphics.lanterna.drawer;

import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.software_engineering_professor.board.Board;
import com.software_engineering_professor.geom.Point;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

public class BoardDrawer extends Drawer{

    public BoardDrawer(Point origin) {
        super(origin);
    }

    public Collection<DrawPoints> getDrawPoints(Board b) {
        Objects.requireNonNull(b);

        Collection<DrawPoints> points = new ArrayList<>();
        int width = b.getWidth();
        int height = b.getHeight();

        Collection<Point> pointsHeight = new ArrayList<>();
        for(int i = 0; i <= height; i++) {
            pointsHeight.add(new Point(-1, i));
            pointsHeight.add(new Point(width, i));
        }

        points.add(new DrawPoints(transformToOrigin(pointsHeight), getVerticalWallCharacter()));

        Collection<Point> pointsWidth = new ArrayList<>();
        for(int i = -1; i <= width; i++) {
            pointsWidth.add(new Point(i, height));
        }

        points.add(new DrawPoints(transformToOrigin(pointsWidth), getHorizontalWallCharacter()));

        return points;
    }

    private TextCharacter getHorizontalWallCharacter() {
        return new TextCharacter('_', TextColor.ANSI.DEFAULT, TextColor.ANSI.WHITE);
    }

    private TextCharacter getVerticalWallCharacter() {
        return new TextCharacter('|', TextColor.ANSI.DEFAULT, TextColor.ANSI.WHITE);
    }
}
