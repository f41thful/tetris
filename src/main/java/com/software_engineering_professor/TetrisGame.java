package com.software_engineering_professor;

import com.software_engineering_professor.board.Board;
import com.software_engineering_professor.board.BoardImpl;
import com.software_engineering_professor.board.PositionValidation;
import com.software_engineering_professor.engine.GameEngine;
import com.software_engineering_professor.engine.GameEngineFactory;
import com.software_engineering_professor.engine.controller.Controller;
import com.software_engineering_professor.engine.iteration_listener.IterationListener;
import com.software_engineering_professor.piece.PieceLoader;
import com.software_engineering_professor.piece.PieceStore;

public class TetrisGame {
    private static final float ITERATION_PERIOD_SC = 0.05f;
    private static final int MOVE_DOWN_PERIOD = 4;

    private Board board;
    private GameEngine gameEngine;

    public TetrisGame(int width, int height, Controller selectedPieceController) {
        board = new BoardImpl(width, height);
        PieceLoader pieceLoader = new PieceLoader(new PositionValidation(board));

        checkIfTheLongestPieceFitInTheMiddleOfTheBoard(width, pieceLoader);

        PieceStore pieceStore = new PieceStore();
        pieceLoader.getPieces().forEach(pieceStore::addPiece);

        gameEngine = new GameEngineFactory().create(board, pieceStore, ITERATION_PERIOD_SC, MOVE_DOWN_PERIOD, selectedPieceController);
    }

    public void start() throws InterruptedException {
        gameEngine.start();
    }

    public Board getBoard() {
        return board;
    }

    public void addIterationListener(IterationListener iterationListener) {
        gameEngine.addIterationListener(iterationListener);
    }

    // when a piece is too long and place in the middle, even if you have room for it to go down it can collide with the
    // walls.
    private void checkIfTheLongestPieceFitInTheMiddleOfTheBoard(int width, PieceLoader pieceLoader) {
        int maxWidth = pieceLoader.getMaxWidth();
        if(width < 2 * maxWidth) {
            throw new IllegalStateException("Board width has to be enough to hold the longest piece when placed" +
                    " in the middle of the board. Width is " + width + ", the longest piece is " + maxWidth);
        }
    }

}
