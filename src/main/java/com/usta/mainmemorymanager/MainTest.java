package com.usta.mainmemorymanager;

import com.usta.mainmemorymanager.service.SimulationService;

public class MainTest {

    public static void main(String[] args) {
        int sizeMemory = 4000;
        SimulationService service = new SimulationService(sizeMemory);
       service.startSimulation(20);
    }
}
