package com.software_engineering_professor.engine.event;

import com.software_engineering_professor.piece.Piece;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.software_engineering_professor.engine.event.EventType.MOVE_DOWN;
import static com.software_engineering_professor.engine.event.EventType.MOVE_HORIZONTAL;
import static com.software_engineering_professor.engine.event.EventType.ROTATE_LEFT;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;

class EventQueueTest {
    private EventQueue eventQueue;
    private List<Piece> pieces;

    @BeforeEach
    public void setUp() {
        eventQueue = new EventQueue(new ArrayList<>());
        pieces = new ArrayList<>();
        for(int i = 0; i < 5; i++) {
            pieces.add(mock(Piece.class));
        }
        eventQueue.addIfNotPresent(pieces);
    }

    @Test
    public void given0Events_thenNoEventIsReceived() {
        eventQueue.performEvents();
        pieces.forEach(Mockito::verifyNoInteractions);
    }

    @Test
    public void given1Event_thenOnly1EventIsReceivedByAllThePieces() {
        eventQueue.addEvent(new Event(MOVE_DOWN, 1));
        eventQueue.performEvents();
        pieces.forEach(piece -> verify(piece, times(1)).moveDown(1));
    }

    @Test
    public void givenNEvents_thenAllAreDeliveredToAllThePieces() {
        int numberMoveDown = addRandomNumberOfEvents(MOVE_DOWN, eventQueue);
        int numberMoveHorizontal = addRandomNumberOfEvents(MOVE_HORIZONTAL, eventQueue);
        int numberRotateLeft = addRandomNumberOfEvents(ROTATE_LEFT, eventQueue);

        eventQueue.performEvents();
        pieces.forEach(piece -> {
            verify(piece, times(numberMoveDown)).moveDown(1);
            verify(piece, times(numberMoveHorizontal)).moveHorizontal(1);
            verify(piece, times(numberRotateLeft)).rotateLeft();
        });
    }

    @Test
    public void givenAPieceIsAddedTwice_thenTheSecondOneIsDiscarded() {
        eventQueue.addIfNotPresent(pieces);
        eventQueue.addEvent(new Event(MOVE_DOWN, 1));

        eventQueue.performEvents();

        pieces.forEach(piece -> verify(piece, times(1)).moveDown(1));
    }

    @Test
    public void givenAllDownMovementsWereValid_thenTrue() {
        pieces.forEach(piece -> doReturn(true).when(piece).moveDown(1));
        eventQueue.addEvent(new Event(MOVE_DOWN, 1));
        assertTrue(eventQueue.performEvents());
    }

    @RepeatedTest(3)
    public void givenAtLeastOneDownMovementWasInvalid_thenFalse() {
        pieces.forEach(piece -> doReturn(true).when(piece).moveDown(1));
        Random random = new Random();
        int index = random.nextInt(pieces.size());
        doReturn(false).when(pieces.get(index)).moveDown(1);

        eventQueue.addEvent(new Event(MOVE_DOWN, 1));
        assertFalse(eventQueue.performEvents());
    }

    @Test
    public void givenAllDownMovementsWereInvalid_thenDontApplyEventsToThosePiecesAnymore() {
        pieces.forEach(piece -> doReturn(false).when(piece).moveDown(1));
        eventQueue.addEvent(new Event(MOVE_DOWN, 1));
        assertFalse(eventQueue.performEvents());

        eventQueue.addEvent(new Event(MOVE_DOWN, 1));
        eventQueue.performEvents();
        pieces.forEach(piece -> verify(piece, times(1)).moveDown(1));
    }

    @Test
    public void givenAllDownMovementsWereNotInvalid_thenAllThePiecesAreThereForNextEvents() {
        pieces.forEach(piece -> doReturn(true).when(piece).moveDown(1));
        doReturn(false).when(pieces.get(0)).moveDown(1);

        eventQueue.addEvent(new Event(MOVE_DOWN, 1));
        assertFalse(eventQueue.performEvents());

        eventQueue.addEvent(new Event(MOVE_DOWN, 1));
        assertFalse(eventQueue.performEvents());

        pieces.forEach(piece -> verify(piece, times(2)).moveDown(1));
    }

    private int addRandomNumberOfEvents(EventType eventType, EventQueue eventQueue) {
        Random random = new Random();
        int limit = random.nextInt(7);

        for(int i = 0; i < limit; i++) {
            eventQueue.addEvent(new Event(eventType, 1));
        }

        return limit;
    }
}