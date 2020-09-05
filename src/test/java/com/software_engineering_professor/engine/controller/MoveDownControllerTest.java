package com.software_engineering_professor.engine.controller;

import com.software_engineering_professor.engine.event.EventQueue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;

class MoveDownControllerTest {
    private MoveDownController moveDownController;
    private EventQueue eventQueue;
    private int period = 3;

    @BeforeEach
    public void setUp() {
        moveDownController = new MoveDownController(period);
        eventQueue = mock(EventQueue.class);
        moveDownController.setEventQueue(eventQueue);
    }

    @Test
    public void givenItWasNeverCalled_thenNoEventIssued() {
        verifyNoInteractions(eventQueue);
    }

    @Test
    public void givenThePeriodIsNotReached_thenNoEventIssued() {
        moveDownController.addEvents(0);
        moveDownController.addEvents(1);

        verifyNoInteractions(eventQueue);
    }

    @Test
    public void givenPeriodIsExactlyReached_thenJust1EventIsIssued() {
        for(int i = 0; i < period; i++) {
            moveDownController.addEvents(i);
        }

        verify(eventQueue, times(1)).addEvent(any());
    }

    @Test
    public void givenPeriodIsSurpassedOnce_thenJust1EventIsIssued() {
        int limit = 2 * period - 1;

        for(int i = 0; i < limit; i++) {
            moveDownController.addEvents(i);
        }

        verify(eventQueue, times(1)).addEvent(any());
    }

    @Test
    public void givenAtLeastTwoPeriods_thenAtLeast2EventsAreIssued() {
        int limit = 30;

        for(int i = 0; i < limit; i++) {
            moveDownController.addEvents(i);
        }

        verify(eventQueue, times(limit / period)).addEvent(any());
    }
}