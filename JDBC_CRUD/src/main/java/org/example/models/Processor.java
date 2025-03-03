package org.example.models;

public class Processor {
    private int id;
    private int threadsCount;
    private double clockFrequency;
    private double maxFrequency;
    private int cpuCount;

    public Processor() {}

    public Processor(int threadsCount, double clockFrequency, double maxFrequency, int cpuCount) {
        this.threadsCount = threadsCount;
        this.clockFrequency = clockFrequency;
        this.maxFrequency = maxFrequency;
        this.cpuCount = cpuCount;
    }

    public Processor(int id, int threadsCount, double clockFrequency, double maxFrequency, int cpuCount) {
        this.id = id;
        this.threadsCount = threadsCount;
        this.clockFrequency = clockFrequency;
        this.maxFrequency = maxFrequency;
        this.cpuCount = cpuCount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getThreadsCount() {
        return threadsCount;
    }

    public void setThreadsCount(int threadsCount) {
        this.threadsCount = threadsCount;
    }

    public double getClockFrequency() {
        return clockFrequency;
    }

    public void setClockFrequency(double clockFrequency) {
        this.clockFrequency = clockFrequency;
    }

    public double getMaxFrequency() {
        return maxFrequency;
    }

    public void setMaxFrequency(double maxFrequency) {
        this.maxFrequency = maxFrequency;
    }

    public int getCpuCount() {
        return cpuCount;
    }

    public void setCpuCount(int cpuCount) {
        this.cpuCount = cpuCount;
    }
}