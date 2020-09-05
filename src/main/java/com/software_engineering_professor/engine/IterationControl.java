package com.software_engineering_professor.engine;

import com.software_engineering_professor.engine.iteration_listener.IterationListener;

public class IterationControl implements IterationListener {
    private float period; // period in seconds
    private long startTime;
    private long finishTime;

    public IterationControl(float period) {
        if(period <= 0) {
            throw new IllegalArgumentException("Period cannot be <= 0. It was " + period + ".");
        }

        this.period = period;
    }

    public void blockUntilNextIterationCanStart() throws InterruptedException {
        long elapsed = finishTime - startTime;
        float remaining = period - elapsed;
        if(remaining >= 0) {
            Thread.sleep((long) (remaining * 1000));
        }
    }

    @Override
    public void start(int iteration) {
        startTime = System.currentTimeMillis();
    }

    @Override
    public void finish(int iteration) {
        finishTime = System.currentTimeMillis();
    }
}
