package com.software_engineering_professor.main;

import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.software_engineering_professor.board.Board;
import com.software_engineering_professor.board.BoardImpl;
import com.software_engineering_professor.geom.Point;
import com.software_engineering_professor.graphics.lanterna.BoardDrawer;
import com.software_engineering_professor.graphics.lanterna.DrawPoints;
import com.software_engineering_professor.graphics.lanterna.PieceDrawer;
import com.software_engineering_professor.piece.Piece;
import com.software_engineering_professor.piece.PieceBuilder;

import java.io.IOException;
import java.util.Collection;

public class GraphicsTest {
    public static void main(String[] args) throws IOException {
        DefaultTerminalFactory defaultTerminalFactory = new DefaultTerminalFactory();
        Screen screen = null;

        Terminal terminal = defaultTerminalFactory.createTerminal();
        screen = new TerminalScreen(terminal);
        screen.startScreen();
        screen.setCursorPosition(null);

        Board board = new BoardImpl(40, 20);

        Piece piece =
        PieceBuilder.create(0).add("x  ")
                              .add("x  ")
                              .add("xx ")
                              .build();


        PieceDrawer pieceDrawer = new PieceDrawer();
        BoardDrawer boardDrawer = new BoardDrawer();

        draw(screen, boardDrawer.getDrawPoints(board));
        draw(screen, pieceDrawer.getDrawPoints(piece));

        screen.refresh();
    }

    private static void draw(Screen screen, Collection<DrawPoints> drawPointss) {
        for(DrawPoints drawPoints : drawPointss) {
            draw(screen, drawPoints);
        }
    }

    private static void draw(Screen screen, DrawPoints drawPoints) {
        for(Point p : drawPoints.points) {
            screen.setCharacter(p.x, p.y, drawPoints.character);
        }
    }
}
