package org.example.models;

public class GraphicCard {
    private int id;
    private int gpuCount;
    private double gpuFrequency;
    private int memoryCount;
    private double memoryFrequency;

    public GraphicCard() {}

    public GraphicCard(int gpuCount, double gpuFrequency, int memoryCount, double memoryFrequency) {
        this.gpuCount = gpuCount;
        this.gpuFrequency = gpuFrequency;
        this.memoryCount = memoryCount;
        this.memoryFrequency = memoryFrequency;
    }

    public GraphicCard(int id, int gpuCount, double gpuFrequency, int memoryCount, double memoryFrequency) {
        this.id = id;
        this.gpuCount = gpuCount;
        this.gpuFrequency = gpuFrequency;
        this.memoryCount = memoryCount;
        this.memoryFrequency = memoryFrequency;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGpuCount() {
        return gpuCount;
    }

    public void setGpuCount(int gpuCount) {
        this.gpuCount = gpuCount;
    }

    public double getGpuFrequency() {
        return gpuFrequency;
    }

    public void setGpuFrequency(double gpuFrequency) {
        this.gpuFrequency = gpuFrequency;
    }

    public int getMemoryCount() {
        return memoryCount;
    }

    public void setMemoryCount(int memoryCount) {
        this.memoryCount = memoryCount;
    }

    public double getMemoryFrequency() {
        return memoryFrequency;
    }

    public void setMemoryFrequency(double memoryFrequency) {
        this.memoryFrequency = memoryFrequency;
    }
}