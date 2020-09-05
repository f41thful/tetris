package com.software_engineering_professor.graphics.lanterna.controller;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.software_engineering_professor.engine.controller.Controller;
import com.software_engineering_professor.engine.event.Event;
import com.software_engineering_professor.engine.event.EventQueue;
import com.software_engineering_professor.graphics.lanterna.TetrisGUI;

import java.io.IOException;
import java.util.Objects;

import static com.software_engineering_professor.engine.event.EventType.MOVE_DOWN;
import static com.software_engineering_professor.engine.event.EventType.MOVE_HORIZONTAL;
import static com.software_engineering_professor.engine.event.EventType.ROTATE_LEFT;

public class InputController implements Controller {
    private static final int MAX_NUMBER_OF_EVENTS_PER_CALL = 10;

    private EventQueue eventQueue;
    private TetrisGUI keyStrokeProducer;

    public InputController(TetrisGUI keyStrokeProducer) {
        Objects.requireNonNull(keyStrokeProducer);
        this.keyStrokeProducer = keyStrokeProducer;
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
                KeyStroke keyStroke = keyStrokeProducer.pollInput();
                Event event = getEvent(keyStroke);
                if(event != null) {
                    eventQueue.addEvent(event);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private Event getEvent(KeyStroke keyStroke) {
        if(keyStroke == null) {
            return null;
        } else {
            return getEvent(keyStroke.getKeyType());
        }
    }

    private Event getEvent(KeyType keyType) {
        switch (keyType) {
            case ArrowLeft:
                return new Event(MOVE_HORIZONTAL, -1);
            case ArrowRight:
                return new Event(MOVE_HORIZONTAL, 1);
            case ArrowDown:
                return new Event(MOVE_DOWN, 1);
            case ArrowUp:
                return new Event(ROTATE_LEFT, 1);
            default:
                return null;
        }
    }
}
