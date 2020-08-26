package com.software_engineering_professor.graphics.lanterna;

import com.googlecode.lanterna.TextCharacter;
import com.software_engineering_professor.geom.Point;

import java.util.Collection;

public class DrawPoints {
    public Collection<Point> points;
    public TextCharacter character;

    public DrawPoints(Collection<Point> points, TextCharacter character) {
        this.points = points;
        this.character = character;
    }
}
