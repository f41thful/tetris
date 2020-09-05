package com.software_engineering_professor.engine;

import com.software_engineering_professor.board.Board;
import com.software_engineering_professor.engine.controller.Controller;
import com.software_engineering_professor.engine.controller.MoveDownController;
import com.software_engineering_professor.engine.event.EventQueue;
import com.software_engineering_professor.geom.Point;
import com.software_engineering_professor.piece.PieceStore;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class GameEngineFactory {
    public GameEngine create(Board board, PieceStore pieceStore, float iterationPeriodSc, int moveDownPeriod) {
        Objects.requireNonNull(board);
        Objects.requireNonNull(pieceStore);

        Point origin = new Point(board.getWidth() / 2, 0);
        PieceGenerator pieceGenerator = new PieceGenerator(pieceStore, new Random(seed()), origin);

        Controller moveDownController = new MoveDownController(moveDownPeriod);
        EventQueue moveDownEventQueue = new EventQueue(new ArrayList<>());
        moveDownController.setEventQueue(moveDownEventQueue);

        Controller selectedPieceController = new MoveDownController(moveDownPeriod);
        EventQueue selectedPieceEventQueue = new EventQueue(new ArrayList<>());
        selectedPieceController.setEventQueue(selectedPieceEventQueue);

        IterationControl iterationControl = new IterationControl(iterationPeriodSc);

        return new GameEngine(board, pieceGenerator, moveDownEventQueue, selectedPieceEventQueue
        , moveDownController, selectedPieceController, iterationControl);
    }

    private long seed() {
        return System.nanoTime();
    }
}
