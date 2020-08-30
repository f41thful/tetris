package com.software_engineering_professor.engine.event;

public class Event {
    private EventType eventType;
    private int value;

    public Event(EventType eventType, int value) {
        this.eventType = eventType;
        this.value = value;
    }

    public EventType getEventType() {
        return eventType;
    }

    public int getValue() {
        return value;
    }
}
