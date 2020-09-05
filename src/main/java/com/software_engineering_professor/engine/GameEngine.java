package com.software_engineering_professor.engine;

import com.software_engineering_professor.board.Board;
import com.software_engineering_professor.engine.controller.Controller;
import com.software_engineering_professor.engine.event.EventQueue;
import com.software_engineering_professor.piece.Piece;

import java.util.Collection;
import java.util.Objects;

public class GameEngine {
    private Board board;
    private PieceGenerator pieceGenerator;
    private EventQueue moveDownEventQueue;
    private EventQueue selectedPieceEventQueue; //for player actions
    private Controller moveDownController;
    private Controller selectedPieceController;

    public GameEngine(Board board, PieceGenerator pieceGenerator,
                      EventQueue moveDownEventQueue, EventQueue selectedPieceEventQueue, Controller moveDownController,
                      Controller selectedPieceController) {
        Objects.requireNonNull(this.board);
        Objects.requireNonNull(this.pieceGenerator);
        Objects.requireNonNull(this.moveDownEventQueue);
        Objects.requireNonNull(this.selectedPieceEventQueue);
        Objects.requireNonNull(this.moveDownController);
        Objects.requireNonNull(this.selectedPieceController);

        this.board = board;
        this.pieceGenerator = pieceGenerator;
        this.moveDownEventQueue = moveDownEventQueue;
        this.selectedPieceEventQueue = selectedPieceEventQueue;
        this.moveDownController = moveDownController;
        this.selectedPieceController = selectedPieceController;
    }

    private boolean isFinished;

    public void start() {
        int iteration = 0;

        while(!isFinished) {
            isFinished = simulateIteration(iteration);
            iteration++;
        }
    }

    private boolean simulateIteration(int iteration) {
        if (!addValidPieceIfNoneSelected()) {
            System.out.println("Game finished");
            return true;
        }

        boolean allValid = processEvents(iteration);

        processCompletedLines(allValid);
        return false;
    }

    private void processCompletedLines(boolean allValid) {
        if(!allValid) {
            Collection<Integer> completedLines = board.detectAndDeleteCompletedLines();
            if(!completedLines.isEmpty()) {
                int maxLine = completedLines.stream().max(Integer::compareTo).get();
                moveDownEventQueue.addIfNotPresent(board.getPiecesAboveOrInLineOrderedByHeightDesc(maxLine));
            }
        }
    }

    private boolean processEvents(int iteration) {
        moveDownController.addEvents(iteration);
        selectedPieceController.addEvents(iteration);

        boolean allValid = moveDownEventQueue.performEvents();
        allValid &= selectedPieceEventQueue.performEvents();
        return allValid;
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
