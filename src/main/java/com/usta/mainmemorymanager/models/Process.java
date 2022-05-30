package com.usta.mainmemorymanager.models;

import lombok.Data;

import java.util.Random;
import java.util.UUID;

@Data

public class Process {
    private String id;
    private String name;
    private int startLocation;
    private int endLocation;
    private int size;
    private int startTime;
    private int endTime;
    private int duration;
    private int timeToFinish;
    private ProcessState state;

    public Process(String name) {
        this.id = getId();
        this.size = generateRandomNumber(2, 64);
        this.duration = generateRandomNumber(2, 64);
        this.timeToFinish = duration;
        this.state = ProcessState.WAITING_FOR;
        this.name = name;
    }


    public int generateRandomNumber(int min, int max) {
        return new Random().nextInt(max - min) + min;
    }

    public void start(int location, int currentTime) {
        state = ProcessState.RUNNING;
        startTime = currentTime;
        startLocation = location;
        endLocation = startLocation + size;
    }

    public void finish(int currentTime) {
       state = ProcessState.FINISHED;
       endTime = currentTime;
    }

    public boolean inspectProcess() {
        if (state == ProcessState.RUNNING){
            timeToFinish--;
            return timeToFinish==0;
        }
        return false;
    }

    public static String generateId() {
        return UUID.randomUUID().toString();
    }


}
