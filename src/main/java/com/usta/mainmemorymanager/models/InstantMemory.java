package com.usta.mainmemorymanager.models;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class InstantMemory {

    private int clock;
    private int numberProcess;
    private FreeMemory freeMemory;
    private WaitingProcess waitingProcess;
    private List<Process> processesRunning;

    public InstantMemory(int clock, int numberProcess, FreeMemory freeMemory, WaitingProcess waitingProcess, List<Process> processesRunning) {
        Gson gson = new Gson();
        this.clock = clock;
        this.numberProcess = numberProcess;
        this.freeMemory = gson.fromJson(gson.toJson(freeMemory), FreeMemory.class);
        this.waitingProcess =  gson.fromJson(gson.toJson(waitingProcess), WaitingProcess.class);
        this.processesRunning =  gson.fromJson(gson.toJson(processesRunning), new TypeToken<ArrayList<Process>>(){}.getType());
    }
}
