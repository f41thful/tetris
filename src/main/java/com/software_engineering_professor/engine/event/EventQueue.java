package com.software_engineering_professor.engine.event;

import com.software_engineering_professor.piece.Piece;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.software_engineering_professor.engine.event.EventType.MOVE_DOWN;

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
        for(Piece p : pieces) {
            for(Event e : events) {
                try {
                    switch (e.getEventType()) {
                        case MOVE_DOWN:
                            moveDownAlwaysValid &= p.moveDown(e.getValue());
                            break;
                        case MOVE_HORIZONTAL:
                            p.moveHorizontal(e.getValue());
                            break;
                        case ROTATE_LEFT:
                            p.rotateLeft();
                            break;
                    }
                } catch(Exception ex) {
                    System.out.println("Event " + e + " was not delivered because of " + ex.getMessage());
                    System.out.println(e);
                }
            }
        }

        return moveDownAlwaysValid;
    }
}
