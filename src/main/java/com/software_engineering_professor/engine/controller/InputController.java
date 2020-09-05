package com.software_engineering_professor.engine.controller;

import com.software_engineering_professor.engine.event.EventQueue;

public class InputController implements Controller{
    private EventQueue eventQueue;

    @Override
    public void setEventQueue(EventQueue eventQueue) {
        this.eventQueue = eventQueue;
    }

    @Override
    public void addEvents(int iteration) {

    }
}
