package com.usta.mainmemorymanager.models;



public class Process {

    int id;
    int startLocation;
    int endLocation;
    int size;
    int duration;
    int timeToFinish;

    enum state {

    }

    public Process() {
        this.id = id;
        this.startLocation = startLocation;
        this.endLocation = endLocation;
        this.size = endLocation - startLocation;
        this.duration = getDuration();
        this.timeToFinish = getTimeToFinish();
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

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getTimeToFinish() {
        return timeToFinish;
    }

    public void setTimeToFinish(int timeToFinish) {
        this.timeToFinish = timeToFinish;
    }


    private void Process(int size) {
        int flag = -1;
        int max = 0;





    }
}
