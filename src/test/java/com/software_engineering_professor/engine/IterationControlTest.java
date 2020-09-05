package com.software_engineering_professor.engine;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Tag("slow")
@Tag("time-dependent")
class IterationControlTest {
    private static final float PERIOD = 1.0f;
    private static final float DELTA = 0.01f;

    private IterationControl iterationControl;

    @BeforeEach
    public void setUp() {
        iterationControl = new IterationControl(PERIOD);
    }

    @Test
    public void givenNoTimeForSimulation_thenWaitPeriod() throws InterruptedException {
        long start = getCurrentTime();
        iterationControl.blockUntilNextIterationCanStart();
        long finish = getCurrentTime();

        assertEquals(PERIOD, seconds(finish - start), DELTA);
    }

    @Test
    public void givenHalfTimeForSimulation_thenWaitHalfThePeriod() throws InterruptedException {
        float halfPeriod = PERIOD / 2;

        iterationControl.start(0);
        Thread.sleep(millis(halfPeriod));
        iterationControl.finish(0);

        long start = getCurrentTime();
        iterationControl.blockUntilNextIterationCanStart();
        long finish = getCurrentTime();

        assertEquals(halfPeriod, seconds(finish - start), DELTA);
    }

    @Test
    public void givenWholeTimeForSimulation_thenWaitNothing() throws InterruptedException {
        iterationControl.start(0);
        Thread.sleep(millis(PERIOD));
        iterationControl.finish(0);

        long start = getCurrentTime();
        iterationControl.blockUntilNextIterationCanStart();
        long finish = getCurrentTime();

        assertEquals(0, seconds(finish - start), DELTA);
    }

    @Test
    public void givenMoreTimeForSimulation_thenWaitNothing() throws InterruptedException {
        iterationControl.start(0);
        Thread.sleep(millis(2 * PERIOD));
        iterationControl.finish(0);

        long start = getCurrentTime();
        iterationControl.blockUntilNextIterationCanStart();
        long finish = getCurrentTime();

        assertEquals(0, seconds(finish - start), DELTA);
    }

    private float seconds(long nanos) {
        return ((float) nanos) / 1_000_000_000;
    }

    private long millis(float seconds) {
        return (long) (seconds * 1000);
    }

    private long getCurrentTime() {
        return System.nanoTime();
    }
}