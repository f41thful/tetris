package com.software_engineering_professor.graphics.lanterna.drawer;

import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.software_engineering_professor.geom.Point;
import com.software_engineering_professor.piece.Piece;

import java.util.Objects;

import static com.googlecode.lanterna.TextColor.ANSI.BLUE;
import static com.googlecode.lanterna.TextColor.ANSI.CYAN;
import static com.googlecode.lanterna.TextColor.ANSI.GREEN;
import static com.googlecode.lanterna.TextColor.ANSI.MAGENTA;
import static com.googlecode.lanterna.TextColor.ANSI.RED;
import static com.googlecode.lanterna.TextColor.ANSI.WHITE;
import static com.googlecode.lanterna.TextColor.ANSI.YELLOW;

public class PieceDrawer extends Drawer {
    private TextColor.ANSI[] colors = new TextColor.ANSI[] {
            RED, GREEN, YELLOW, BLUE, MAGENTA, CYAN, WHITE
    };

    public PieceDrawer(Point origin) {
        super(origin);
    }

    public DrawPoints getDrawPoints(Piece p) {
        Objects.requireNonNull(p);
        TextCharacter textCharacter = new TextCharacter(' ', TextColor.ANSI.DEFAULT, getColor(p.getType()));

        return new DrawPoints(transformToOrigin(p.getGlobalPoints()), textCharacter);
    }

    private TextColor getColor(int type) {
        int index = type % colors.length;

        return colors[index];
    }
}
