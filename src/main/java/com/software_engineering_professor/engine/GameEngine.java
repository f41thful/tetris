package com.software_engineering_professor.engine;

import com.software_engineering_professor.board.Board;
import com.software_engineering_professor.engine.controller.Controller;
import com.software_engineering_professor.engine.event.EventQueue;
import com.software_engineering_professor.engine.iteration_listener.IterationListener;
import com.software_engineering_professor.piece.Piece;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

public class GameEngine {
    private Board board;
    private PieceGenerator pieceGenerator;
    private EventQueue moveDownEventQueue;
    private EventQueue selectedPieceEventQueue; //for player actions
    private Controller moveDownController;
    private Controller selectedPieceController;
    private IterationControl iterationControl;

    private Collection<IterationListener> iterationListeners;

    public GameEngine(Board board, PieceGenerator pieceGenerator,
                      EventQueue moveDownEventQueue, EventQueue selectedPieceEventQueue, Controller moveDownController,
                      Controller selectedPieceController, IterationControl iterationControl) {
        Objects.requireNonNull(board);
        Objects.requireNonNull(pieceGenerator);
        Objects.requireNonNull(moveDownEventQueue);
        Objects.requireNonNull(selectedPieceEventQueue);
        Objects.requireNonNull(moveDownController);
        Objects.requireNonNull(selectedPieceController);
        Objects.requireNonNull(iterationControl);

        this.board = board;
        this.pieceGenerator = pieceGenerator;
        this.moveDownEventQueue = moveDownEventQueue;
        this.selectedPieceEventQueue = selectedPieceEventQueue;
        this.moveDownController = moveDownController;
        this.selectedPieceController = selectedPieceController;
        this.iterationControl = iterationControl;

        iterationListeners = new ArrayList<>();
        iterationListeners.add(iterationControl);
    }

    private boolean isFinished;

    public void start() throws InterruptedException {
        int iteration = 0;

        while(!isFinished) {
            notifyStart(iteration);
            isFinished = simulateIteration(iteration);
            notifyFinish(iteration);

            iteration++;
            iterationControl.blockUntilNextIterationCanStart();
        }
    }

    public void addIterationListener(IterationListener iterationListener) {
        Objects.requireNonNull(iterationListener);
        iterationListeners.add(iterationListener);
    }

    private void notifyStart(int iteration) {
        iterationListeners.forEach(listener -> listener.start(iteration));
    }

    private void notifyFinish(int iteration) {
        iterationListeners.forEach(listener -> listener.finish(iteration));
    }

    private boolean simulateIteration(int iteration) {
        if(isFinished) {
            System.out.println("Trying to simulate when the game is already finished.");
            return true;
        }

        if (!addValidPieceIfNoneSelected()) {
            System.out.println("Game finished");
            return true;
        }

        boolean isBoardStill = processEvents(iteration);

        processCompletedLines(isBoardStill);
        return false;
    }

    private void processCompletedLines(boolean isBoardStill) {
        if(isBoardStill) {
            Collection<Integer> completedLines = board.detectAndDeleteCompletedLines();
            if(!completedLines.isEmpty()) {
                int maxLine = completedLines.stream().max(Integer::compareTo).get();
                moveDownEventQueue.addIfNotPresent(board.getPiecesAboveOrInLineOrderedByHeightDesc(maxLine));
            }
        }
    }

    private boolean processEvents(int iteration) {
        boolean isBoardStill;
        if(moveDownEventQueue.hasPieces()) {
            moveDownController.addEvents(iteration);
            isBoardStill = moveDownEventQueue.performEvents();
        } else {
            selectedPieceController.addEvents(iteration);
            isBoardStill = selectedPieceEventQueue.performEvents();
        }

        return isBoardStill;
    }

    /*
    return false only when the piece was not valid.
     */
    private boolean addValidPieceIfNoneSelected() {
        boolean isValid = true;

        if(!selectedPieceEventQueue.hasPieces()) {
            Piece piece = pieceGenerator.next();
            if(!piece.isValidPosition()) {
                System.out.println("Piece " + piece + " could not be placed in a valid position.");
                isValid = false;
            }

            board.addPiece(piece);
            selectedPieceEventQueue.addIfNotPresent(piece);
        }

        return isValid;
    }
}
