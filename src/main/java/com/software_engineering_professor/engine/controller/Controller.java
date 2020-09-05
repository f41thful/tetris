package com.software_engineering_professor.engine.controller;

import com.software_engineering_professor.engine.event.EventQueue;

import java.io.IOException;

public interface Controller {
    void setEventQueue(EventQueue eventQueue);
    void addEvents(int iteration) throws IOException;
}
