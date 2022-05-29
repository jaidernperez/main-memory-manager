package com.usta.mainmemorymanager.models;

import lombok.Data;

import java.util.LinkedList;
import java.util.Queue;

@Data
public class WaitingProcess {

    private Queue<Process> queuedProcesses;

    public WaitingProcess() {
        queuedProcesses = new LinkedList<>();
    }

    public void addProcessToQueue(Process process) {
        queuedProcesses.add(process);
    }

    public Process getProcess() {
        return queuedProcesses.poll();
    }
}
