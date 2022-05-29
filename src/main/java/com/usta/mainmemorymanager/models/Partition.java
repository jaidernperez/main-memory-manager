package com.usta.mainmemorymanager.models;

public class Partition implements Comparable<Partition>{

    int id;
    int startLocation;
    int endLocation;
    int size;
    int idArea;
    boolean occupied;

    public Partition(int id, int startLocation, int endLocation) {
        this.id = id;
        this.startLocation = startLocation;
        this.endLocation = endLocation;
        this.size = endLocation - startLocation;
        this.idArea = 0;
        this.occupied = false;
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

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getIdArea() {
        return idArea;
    }

    public void setIdArea(int idArea) {
        this.idArea = idArea;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    @Override
    public int compareTo(Partition partition) {
        if (this.size < partition.getSize()) {
            return -1;
        } else if (this.size > partition.getSize()) {
            return 1;
        }

        return 0;
    }

    @Override
    public String toString() {
        String name = "free";
        if (this.idArea > 0) { name = "block " + this.idArea; }
        return "\n" + this.startLocation + " - " + this.endLocation + " " + name + " (size " + this.size + ")";
    }
}
