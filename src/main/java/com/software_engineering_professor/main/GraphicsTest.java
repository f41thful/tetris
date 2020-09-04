package com.software_engineering_professor.main;

import com.software_engineering_professor.board.Board;
import com.software_engineering_professor.board.BoardImpl;
import com.software_engineering_professor.board.PositionValidation;
import com.software_engineering_professor.geom.Point;
import com.software_engineering_professor.graphics.lanterna.DrawerFactory;
import com.software_engineering_professor.graphics.lanterna.GeneralDrawer;
import com.software_engineering_professor.graphics.lanterna.ScreenManager;
import com.software_engineering_professor.piece.Piece;
import com.software_engineering_professor.piece.PieceBuilder;

import java.io.IOException;

public class GraphicsTest {
    public static void main(String[] args) throws IOException {
        Point screenOrigin = new Point(20, 2);

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

        GeneralDrawer drawer = new DrawerFactory(screenOrigin).generalDrawer();

        ScreenManager screenManager = new ScreenManager();

        for(int i = 0; i < 100; i++) {
            screenManager.add(drawer.getAllDrawPoints(board));
            screenManager.draw();
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
}
