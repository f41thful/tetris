package com.software_engineering_professor.engine.event;

import java.util.Comparator;

import static com.software_engineering_professor.engine.event.EventType.MOVE_DOWN;

public class EventComparator implements Comparator<Event> {
    @Override
    public int compare(Event o1, Event o2) {
        if(o1 == o2) {
            return 0;
        }

        if(o1 == null || o2 == null) {
            throw new IllegalArgumentException("Just one of them is null.");
        }

        if(o1.getEventType() == MOVE_DOWN) {
            return 1;
        } else if (o2.getEventType() == MOVE_DOWN) {
            return -1;
        }

        return 0;
    }
}
