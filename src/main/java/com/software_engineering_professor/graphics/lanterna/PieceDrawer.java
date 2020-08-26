package com.software_engineering_professor.graphics.lanterna;

import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.software_engineering_professor.piece.Piece;

import java.util.Objects;

public class PieceDrawer {
    public DrawPoints getDrawPoints(Piece p) {
        Objects.requireNonNull(p);
        TextCharacter textCharacter = new TextCharacter(' ', TextColor.ANSI.DEFAULT, getColor(p.getType()));

        return new DrawPoints(p.getGlobalPoints(), textCharacter);
    }

    private TextColor getColor(int type) {
        TextColor.ANSI[] colors = TextColor.ANSI.values();
        int index = (type % (colors.length - 1)) + 1;

        return colors[index];
    }
}
