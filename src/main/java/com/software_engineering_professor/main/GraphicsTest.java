package com.software_engineering_professor.main;

import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.software_engineering_professor.board.Board;
import com.software_engineering_professor.board.BoardImpl;
import com.software_engineering_professor.board.PositionValidation;
import com.software_engineering_professor.geom.Point;
import com.software_engineering_professor.graphics.lanterna.BoardDrawer;
import com.software_engineering_professor.graphics.lanterna.DrawPoints;
import com.software_engineering_professor.graphics.lanterna.DrawerFactory;
import com.software_engineering_professor.graphics.lanterna.PieceDrawer;
import com.software_engineering_professor.piece.Piece;
import com.software_engineering_professor.piece.PieceBuilder;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;

public class GraphicsTest {
    public static void main(String[] args) throws IOException {
        Point screenOrigin = new Point(20, 2);
        DefaultTerminalFactory defaultTerminalFactory = new DefaultTerminalFactory();
        Screen screen = null;

        Terminal terminal = defaultTerminalFactory.createTerminal();
        screen = new TerminalScreen(terminal);
        screen.startScreen();
        screen.setCursorPosition(null);

        Board board = new BoardImpl(40, 20);
        PositionValidation positionValidation = new PositionValidation(board);

        Piece piece =
        PieceBuilder.create(0, new Point(15, 2)).add("x  ")
                              .add("x  ")
                              .add("xx ")
                              .positionValidation(positionValidation)
                              .build();

        Piece piece2 =
        PieceBuilder.create(1, new Point(15, 7))
                              .add("x  ")
                              .add("x  ")
                              .add("xx ")
                              .positionValidation(positionValidation)
                              .build();


        board.addPiece(piece);
        board.addPiece(piece2);

        DrawerFactory factory = new DrawerFactory(screenOrigin);
        PieceDrawer pieceDrawer = factory.pieceDrawer();
        BoardDrawer boardDrawer = factory.boardDrawer();

        for(int i = 0; i < 100; i++) {
            screen.clear();
            draw(screen, boardDrawer.getDrawPoints(board));
            draw(screen, pieceDrawer.getDrawPoints(piece));
            draw(screen, pieceDrawer.getDrawPoints(piece2));
            screen.refresh();
            piece.moveHorizontal(1);

//            switch (i) {
//                case 6:
//                    piece.delete(Collections.singletonList(new Point(0, 0)));
//                    break;
//                case 8:
//                    piece.delete(Collections.singletonList(new Point(0, 1)));
//                    break;
//                case 10:
//                    piece.delete(Collections.singletonList(new Point(0, 2)));
//                    break;
//                case 14:
//                    piece.delete(Collections.singletonList(new Point(1, 2)));
//                    break;
//            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

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
