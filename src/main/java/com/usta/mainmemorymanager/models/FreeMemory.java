package com.usta.mainmemorymanager.models;

import java.util.ArrayList;
import java.util.Collections;

public class FreeMemory {
    int id;
    int startLocation;
    int endLocation;
    ArrayList<FreeBlock>freeBlocksMemory;

    public FreeMemory(String startLocation, String endLocation){
        this.freeBlocksMemory = new ArrayList<FreeBlock>();

        this.startLocation = Integer.parseInt(startLocation);
        this.endLocation = Integer.parseInt(endLocation);

        FreeBlock firstFreeBlock = new FreeBlock(1, this.startLocation, this.endLocation);

        this.freeBlocksMemory.add(firstFreeBlock);
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

    public ArrayList<FreeBlock> getFreeBlocksMemory() {
        return freeBlocksMemory;
    }

    public void setFreeBlocksMemory(ArrayList<FreeBlock> freeBlocksMemory) {
        this.freeBlocksMemory = freeBlocksMemory;
    }

    public void addFree(FreeBlock freeBlock) {
        this.freeBlocksMemory.add(freeBlock);
        Collections.sort(this.freeBlocksMemory, Collections.reverseOrder());
    }

    public FreeBlock getBiggestPartition() {
        return this.freeBlocksMemory.get(0);
    }

    public static ArrayList<FreeBlock> getSortedBlocksBySize(){
        ArrayList<FreeBlock> freeBlocks = getSortedBlocksBySize();
        Collections.sort(freeBlocks);
        return (getSortedBlocksBySize());
    }

    public int updateFreeMemory() {
        int sum = 0;
        for (int i = 0; i < this.freeBlocksMemory.size(); i++) {
            sum += this.freeBlocksMemory.get(i).getSize();
        }
        return sum;
    }

    public FreeBlock removeFree() {
        FreeBlock freeBlock = this.freeBlocksMemory.get(0);
        this.freeBlocksMemory.remove(0);
        return freeBlock;
    }


}
