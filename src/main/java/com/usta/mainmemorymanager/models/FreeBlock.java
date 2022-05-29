package com.usta.mainmemorymanager.models;

import lombok.Data;

@Data
public class FreeBlock {

    private int startLocation;
    private int endLocation;
    private int totalSize;

    public FreeBlock(int startLocation, int endLocation) {
        this.startLocation = startLocation;
        this.endLocation = endLocation;
        calculateTotalSize();
    }

    public void setStartLocation(int startLocation) {
        this.startLocation = startLocation;
        calculateTotalSize();
    }

    public void setEndLocation(int endLocation) {
        this.endLocation = endLocation;
        calculateTotalSize();
    }

    public void calculateTotalSize() {
        this.totalSize = this.endLocation - this.startLocation;
    }
}
