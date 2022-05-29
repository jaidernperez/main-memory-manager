package com.usta.mainmemorymanager.models;

import java.util.ArrayList;
import java.util.Collections;

public class FreeMemory {
    int id;
    int startLocation;
    int endLocation;
    ArrayList<Partition>sizeOfBlock;
    ArrayList<Partition>sizeOfFree;

    public FreeMemory(String startLocation, String endLocation){
        this.sizeOfBlock = new ArrayList<Partition>();
        this.sizeOfFree = new ArrayList<Partition>();

        this.startLocation = Integer.parseInt(startLocation);
        this.endLocation = Integer.parseInt(endLocation);

        Partition firstPartition = new Partition(1, this.startLocation, this.endLocation);

        this.sizeOfBlock.add(firstPartition);
        this.sizeOfFree.add(firstPartition);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStartLocation() {
        return startLocation;
    }

    public void setStartLocation(int startLocation) {
        this.startLocation = startLocation;
    }

    public int getEndLocation() {
        return endLocation;
    }

    public void setEndLocation(int endLocation) {
        this.endLocation = endLocation;
    }

    public ArrayList<Partition> getSizeOfBlock() {
        return sizeOfBlock;
    }

    public void setSizeOfBlock(ArrayList<Partition> sizeOfBlock) {
        this.sizeOfBlock = sizeOfBlock;
    }

    public ArrayList<Partition> getSizeOfFree() {
        return sizeOfFree;
    }

    public void setSizeOfFree(ArrayList<Partition> sizeOfFree) {
        this.sizeOfFree = sizeOfFree;
    }

    public void addFree(Partition partition) {
        this.sizeOfFree.add(partition);
        Collections.sort(this.sizeOfFree, Collections.reverseOrder());
    }

    public Partition getBiggestPartition() {
        return this.sizeOfFree.get(0);
    }

    public Partition removeFree() {
        Partition partition = this.sizeOfFree.get(0);
        this.sizeOfFree.remove(0);
        return partition;
    }

    public int sumFree() {
        int sum = 0;
        for (int i = 0; i < this.sizeOfFree.size(); i++) {
            sum += this.sizeOfFree.get(i).getSize();
        }
        return sum;
    }
}
