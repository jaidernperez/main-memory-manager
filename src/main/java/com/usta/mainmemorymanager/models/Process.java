package com.usta.mainmemorymanager.models;

import com.usta.mainmemorymanager.enums.ProcessState;
import com.usta.mainmemorymanager.utils.RandomUtil;
import lombok.Data;

import static com.usta.mainmemorymanager.utils.Constants.*;

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

    public Process(String nameProcess) {
        id = RandomUtil.generateId();
        name = nameProcess;
        size = RandomUtil.generateRandomNumber(MIN_PROCESS_SIZE, MAX_PROCESS_SIZE);
        duration = RandomUtil.generateRandomNumber(MIN_PROCESS_TIME, MAX_PROCESS_TIME);
        timeToFinish = duration;
        state = ProcessState.WAITING_FOR;
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
        if (state == ProcessState.RUNNING) {
            timeToFinish--;
            return timeToFinish == 0;
        }
        return false;
    }

}
