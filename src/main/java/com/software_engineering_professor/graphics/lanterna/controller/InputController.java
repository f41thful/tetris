package com.software_engineering_professor.graphics.lanterna.controller;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.terminal.Terminal;
import com.software_engineering_professor.engine.controller.Controller;
import com.software_engineering_professor.engine.event.EventQueue;

import java.io.IOException;
import java.util.Objects;

public class InputController implements Controller {
    private static final int MAX_NUMBER_OF_EVENTS_PER_CALL = 10;

    private EventQueue eventQueue;
    private Terminal terminal;

    public InputController(Terminal terminal) {
        Objects.requireNonNull(terminal);
        this.terminal = terminal;
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
        for(int i = 0; i < MAX_NUMBER_OF_EVENTS_PER_CALL; i++) {
            try {
                KeyStroke keyStroke = terminal.pollInput();
                eventQueue.addEvent(getEvent(keyStroke.getCharacter()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
