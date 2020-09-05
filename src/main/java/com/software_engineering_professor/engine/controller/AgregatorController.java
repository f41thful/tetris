package com.software_engineering_professor.engine.controller;

import com.software_engineering_professor.engine.event.EventQueue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class AgregatorController implements Controller{
    private Collection<Controller> controllers;


    public AgregatorController(List<Controller> controllers) {
        Objects.requireNonNull(controllers);
        this.controllers = new ArrayList<>();
        this.controllers.addAll(controllers);
    }

    @Override
    public void setEventQueue(EventQueue eventQueue) {
        controllers.forEach(controller -> controller.setEventQueue(eventQueue));
    }

    @Override
    public void addEvents(int iteration) {
        controllers.forEach(controller -> controller.addEvents(iteration));
    }
}
