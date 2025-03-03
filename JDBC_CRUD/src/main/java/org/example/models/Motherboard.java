package org.example.models;

public class Motherboard {
    private int id;
    private int memorySlots;
    private String chipset;
    private String formFactor;

    public Motherboard() {}

    public Motherboard(int memorySlots, String chipset, String formFactor) {
        this.memorySlots = memorySlots;
        this.chipset = chipset;
        this.formFactor = formFactor;
    }

    public Motherboard(int id, int memorySlots, String chipset, String formFactor) {
        this.id = id;
        this.memorySlots = memorySlots;
        this.chipset = chipset;
        this.formFactor = formFactor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMemorySlots() {
        return memorySlots;
    }

    public void setMemorySlots(int memorySlots) {
        this.memorySlots = memorySlots;
    }

    public String getChipset() {
        return chipset;
    }

    public void setChipset(String chipset) {
        this.chipset = chipset;
    }

    public String getFormFactor() {
        return formFactor;
    }

    public void setFormFactor(String formFactor) {
        this.formFactor = formFactor;
    }
}