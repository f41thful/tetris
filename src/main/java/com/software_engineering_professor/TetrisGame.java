package com.software_engineering_professor;

import com.software_engineering_professor.board.Board;
import com.software_engineering_professor.board.BoardImpl;
import com.software_engineering_professor.board.PositionValidation;
import com.software_engineering_professor.engine.GameEngine;
import com.software_engineering_professor.engine.GameEngineFactory;
import com.software_engineering_professor.engine.iteration_listener.IterationListener;
import com.software_engineering_professor.piece.PieceLoader;
import com.software_engineering_professor.piece.PieceStore;

public class TetrisGame {
    private static final float ITERATION_PERIOD_SC = 1;
    private static final int MOVE_DOWN_PERIOD = 1;

    private Board board;
    private GameEngine gameEngine;

    public TetrisGame(int width, int height) {
        board = new BoardImpl(width, height);
        PieceLoader pieceLoader = new PieceLoader(new PositionValidation(board));

        PieceStore pieceStore = new PieceStore();
        pieceLoader.getDefaultPieces().forEach(pieceStore::addPiece);

        gameEngine = new GameEngineFactory().create(board, pieceStore, ITERATION_PERIOD_SC, MOVE_DOWN_PERIOD);
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
}
