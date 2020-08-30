package com.software_engineering_professor.engine.event;

import com.software_engineering_professor.piece.Piece;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class EventQueue {
    private List<Piece> pieces;
    private List<Event> events;

    public EventQueue(List<Piece> pieces) {
        Objects.requireNonNull(pieces);
        this.pieces = pieces;

        events = new ArrayList<>();
    }

    /*
        Returns true only if for all the pieces, all events of type MOVE_DOWN led to the piece to be in a valid position.
        False otherwise.
     */
    public boolean performEvents() {
        boolean moveDownAlwaysValid = true;
        boolean noPieceCouldMove = true;
        for(Piece p : pieces) {
            for(Event e : events) {
                try {
                    switch (e.getEventType()) {
                        case MOVE_DOWN:
                            boolean couldMove = p.moveDown(e.getValue());
                            moveDownAlwaysValid &= couldMove;
                            noPieceCouldMove &= !couldMove;
                            break;
                        case MOVE_HORIZONTAL:
                            p.moveHorizontal(e.getValue());
                            break;
                        case ROTATE_LEFT:
                            p.rotateLeft();
                            break;
                    }
                } catch(Exception ex) {
                    System.out.println("Event " + e + " was not delivered to piece " + p + " because of " + ex.getMessage());
                    System.out.println(e);
                }
            }
        }

        if(noPieceCouldMove) {
            pieces.clear();
        }

        events.clear();

        return moveDownAlwaysValid;
    }

    public void addIfNotPresent(Collection<Piece> pieces) {
        Objects.requireNonNull(pieces);
        pieces.stream()
              .filter(piece -> !this.pieces.contains(piece))
              .forEach(this.pieces::add);
    }

    public void addEvent(Event e) {
        if(e == null) {
            System.out.println("Null events are not permited.");
            return;
        }

        this.events.add(e);
    }
}
