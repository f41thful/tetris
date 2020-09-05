package com.software_engineering_professor.engine.controller;

import com.software_engineering_professor.engine.event.Event;
import com.software_engineering_professor.engine.event.EventQueue;

import java.util.Objects;

import static com.software_engineering_professor.engine.event.EventType.MOVE_DOWN;

public class MoveDownController implements Controller{
    private int period;  //number of iterations between move down events.
    private EventQueue eventQueue;
    private int counter;

    public MoveDownController(int period) {
        if(period <= 0) {
            throw new IllegalArgumentException("The period must be > 0. It was " + period);
        }

        this.period = period;
        counter = 0;
    }

    @Override
    public void setEventQueue(EventQueue eventQueue) {
        if(this.eventQueue != null) {
            throw new IllegalStateException("Controller " + this + " already has an event queue.");
        }
        Objects.requireNonNull(eventQueue);
        this.eventQueue = eventQueue;
    }

    @Override
    public void addEvents(int iteration) {
        counter++;
        if(counter >= period) {
            counter = 0;
            eventQueue.addEvent(new Event(MOVE_DOWN, 1));
        }
    }
}
