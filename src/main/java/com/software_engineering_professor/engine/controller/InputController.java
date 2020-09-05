package com.software_engineering_professor.engine.controller;

import com.software_engineering_professor.engine.event.EventQueue;

import java.util.Objects;

public class InputController implements Controller{
    private EventQueue eventQueue;

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

    }
}
