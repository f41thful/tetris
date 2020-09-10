package com.software_engineering_professor.engine;

import com.software_engineering_professor.engine.iteration_listener.IterationListener;

public class IterationControl implements IterationListener {
    private float period; // period in seconds
    private long periodNs;
    private long startTimeNs;
    private long finishTimeNs;

    public IterationControl(float period) {
        if(period <= 0) {
            throw new IllegalArgumentException("Period cannot be <= 0. It was " + period + ".");
        }

        this.period = period;
        periodNs = nanos(this.period);
    }

    public void blockUntilNextIterationCanStart() throws InterruptedException {
        long elapsedNs = finishTimeNs - startTimeNs;
        float remainingNs = periodNs - elapsedNs;
//        printTrance(elapsedNs, remainingNs);
        if(remainingNs >= 0) {
//            System.out.println("Wait " + seconds(remainingNs) + " until next iteration.");
            Thread.sleep(millis((long) remainingNs));
        }
    }

    @Override
    public void start(int iteration) {
        startTimeNs = getCurrentTime();
//        System.out.println("----------------------------------------------------");
//        System.out.println("Iteration " + iteration + " starts at " + startTimeNs);
    }

    @Override
    public void finish(int iteration) {
        finishTimeNs = getCurrentTime();
//        System.out.println("Iteration " + iteration + " finishes at " + finishTimeNs);
//        System.out.println("----------------------------------------------------");
    }

    private long getCurrentTime() {
        return System.nanoTime();
    }

    private long nanos(float seconds) {
        return (long) (seconds * 1_000_000_000);
    }

    private long millis(long nanos) {
        return nanos / 1_000_000;
    }

    private float seconds(float nanos) {
        return nanos / 1_000_000_000;
    }

    private void printTrance(long elapsedNs, float remainingNs) {
        StringBuilder sb = new StringBuilder();
        sb.append("Elapsed time ");
        sb.append(seconds(elapsedNs));
        sb.append(" seconds. Remaining time ");
        sb.append(seconds(remainingNs));
        sb.append(" seconds.");

        System.out.println(sb.toString());
    }
}
