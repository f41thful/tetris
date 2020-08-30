package com.software_engineering_professor.engine;

import com.software_engineering_professor.board.Board;
import com.software_engineering_professor.board.BoardLineCompletion;
import com.software_engineering_professor.board.PositionValidation;
import com.software_engineering_professor.engine.event.EventQueue;
import com.software_engineering_professor.piece.Piece;

public class GameEngine {
    private Board board;
    private PositionValidation positionValidation;
    private PieceGenerator pieceGenerator;
    private EventQueue moveDownEventQueue;
    private EventQueue otherEventsEventQueue; //for player actions
    private BoardLineCompletion boardLineCompletion;

    private boolean isFinished;


    public void start() {
        while(!isFinished) {
            if(!moveDownEventQueue.hasPieces()) {
                Piece piece = pieceGenerator.next();
                if(!piece.isValidPosition()) {
                    System.out.println("Game is finished because piece " + piece + " could not be placed in a valid position.");
                    isFinished = true;
                    break;
                }

                moveDownEventQueue.addIfNotPresent(piece);
                otherEventsEventQueue.addIfNotPresent(piece);
            }


        }
    }
}