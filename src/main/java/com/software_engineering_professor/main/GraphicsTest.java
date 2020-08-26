package com.software_engineering_professor.main;

import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.software_engineering_professor.geom.Point;
import com.software_engineering_professor.graphics.lanterna.DrawPoints;
import com.software_engineering_professor.graphics.lanterna.PieceDrawer;
import com.software_engineering_professor.piece.Piece;
import com.software_engineering_professor.piece.PieceBuilder;

import java.io.IOException;

public class GraphicsTest {
    public static void main(String[] args) throws IOException {
        DefaultTerminalFactory defaultTerminalFactory = new DefaultTerminalFactory();
        Screen screen = null;

        Terminal terminal = defaultTerminalFactory.createTerminal();
        screen = new TerminalScreen(terminal);
        screen.startScreen();
        screen.setCursorPosition(null);

        Piece piece =
        PieceBuilder.create(0).add("x  ")
                              .add("x  ")
                              .add("xx ")
                              .build();


        PieceDrawer pieceDrawer = new PieceDrawer();
        DrawPoints drawPoints = pieceDrawer.getDrawPoints(piece);
        for(Point p : drawPoints.points) {
            screen.setCharacter(p.x, p.y, drawPoints.character);
        }

        screen.refresh();
    }
}
