package com.software_engineering_professor.main;

import com.software_engineering_professor.board.Board;
import com.software_engineering_professor.board.BoardImpl;
import com.software_engineering_professor.board.PositionValidation;
import com.software_engineering_professor.engine.PieceGenerator;
import com.software_engineering_professor.geom.Point;
import com.software_engineering_professor.graphics.lanterna.TetrisGUI;
import com.software_engineering_professor.piece.Piece;
import com.software_engineering_professor.piece.PieceLoader;
import com.software_engineering_professor.piece.PieceStore;

import java.io.IOException;
import java.util.Random;

public class GraphicsTest {
    public static void main(String[] args) throws IOException {
        Point screenOrigin = new Point(20, 2);

        Board board = new BoardImpl(40, 20);
        PositionValidation positionValidation = new PositionValidation(board);

        PieceStore pieceStore = new PieceStore();
        PieceLoader pieceLoader = new PieceLoader(positionValidation);

        pieceLoader.getPieces().forEach(pieceStore::addPiece);

        PieceGenerator pieceGenerator = new PieceGenerator(pieceStore, new Random(), new Point(15, 4));


        TetrisGUI gui = new TetrisGUI(screenOrigin, 50, 50);

        for(int i = 0; i < 100; i++) {
            if(i % 4 == 0) {
                board.addPiece(pieceGenerator.next());
            }

            gui.draw(board);

            for(Piece p : board.getPieces()) {
                p.moveDown(1);
            }

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
