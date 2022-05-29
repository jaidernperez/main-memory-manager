package com.usta.mainmemorymanager.service;

import com.usta.mainmemorymanager.enums.ProcessState;
import com.usta.mainmemorymanager.models.*;
import com.usta.mainmemorymanager.models.Process;
import com.usta.mainmemorymanager.utils.RandomUtil;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.usta.mainmemorymanager.utils.Constants.MAX_PROCESS_TO_GENERATE;
import static com.usta.mainmemorymanager.utils.Constants.PROCESS_NAME;

@Data
public class SimulationService {

    private int clock;
    private int numberProcess;
    private FreeMemory freeMemory;
    private WaitingProcess waitingProcess;
    private List<Process> processesRunning;
    private List<InstantMemory> instantMemories;

    public SimulationService(int sizeMemory) {
        instantMemories = new ArrayList<>();
        freeMemory = new FreeMemory(sizeMemory);
        waitingProcess = new WaitingProcess();
        processesRunning = new ArrayList<>();
        clock = 0;
        numberProcess = 1;
    }

    public List<InstantMemory> startSimulation(int numberClockCycles) {
        for (int i = 0; i < numberClockCycles; i++) {
            clock++;
            inspectRunningProcess();
            printMemory(" Before managing new processes ");
            inspectQueuedProcess();
            generateProcess();
            printMemory(" After managing new processes ");
            instantMemories.add(new InstantMemory(clock, numberProcess, freeMemory, waitingProcess, processesRunning));
        }
        return instantMemories;
    }

    private void generateProcess() {
        List<Process> processesGenerated = generateRandomProcess();
        if (processesGenerated.size() > 0) {
            validateProcessSpace(processesGenerated);
        }
    }

    private void inspectRunningProcess() {
        processesRunning.forEach(process -> {
            if (process.inspectProcess()) {
                freeMemory.addFreeBlockMemory(new FreeBlock(process.getStartLocation(), process.getEndLocation()));
                process.finish(clock);
            }
        });
        processesRunning = processesRunning.stream()
                .filter(process -> process.getState() != ProcessState.FINISHED).collect(Collectors.toList());
    }

    private void inspectQueuedProcess() {
        if (waitingProcess.getQueuedProcesses().size() > 0) {
            List<FreeBlock> blocksAvailable = getAvailableFreeMemoryBlocks(waitingProcess.getQueuedProcesses().peek());
            if (blocksAvailable.size() > 0) {
                addProcessToMainMemory(blocksAvailable.get(0), Objects.requireNonNull(waitingProcess.getQueuedProcesses().poll()));
            }
        }
    }

    private void validateProcessSpace(List<Process> processes) {
        for (Process process : processes) {
            List<FreeBlock> blocksAvailable = getAvailableFreeMemoryBlocks(process);
            if (blocksAvailable.size() > 0) {
                addProcessToMainMemory(blocksAvailable.get(0), process);
            } else {
                waitingProcess.addProcessToQueue(process);
            }
        }
    }

    private List<FreeBlock> getAvailableFreeMemoryBlocks(Process process) {
        return freeMemory.getSortedBlocksBySize().stream()
                .filter(freeBlock -> freeBlock.getTotalSize() >= process.getSize())
                .collect(Collectors.toList());
    }

    private void addProcessToMainMemory(FreeBlock blocksAvailable, Process process) {
        process.start(blocksAvailable.getStartLocation(), clock);
        blocksAvailable.setStartLocation(process.getEndLocation());
        blocksAvailable.calculateTotalSize();
        processesRunning.add(process);
    }

    private List<Process> generateRandomProcess() {
        List<Process> processesGenerated = new ArrayList<>();
        for (int i = 0; i < RandomUtil.generateRandomNumber(0, MAX_PROCESS_TO_GENERATE); i++) {
            processesGenerated.add(new Process(String.format(PROCESS_NAME, numberProcess)));
            numberProcess++;
        }
        return processesGenerated;
    }

    public void printMemory(String message) {
        System.out.println("\n............................" + message + "..............................");
        System.out.println("\nTime: " + clock);
        System.out.println("\nRunning process\n");
        for (Process process : processesRunning) {
            System.out.println("\t" + process.toString());
        }
        System.out.println("\nFree memory\n");
        for (FreeBlock block : freeMemory.getFreeBlocksMemory()) {
            System.out.println("\t" + block.toString());
        }
        System.out.println("\nQueue process\n");
        for (Process queuedProcess : waitingProcess.getQueuedProcesses()) {
            System.out.println("\t" + queuedProcess.toString());
        }
        System.out.println("\n.................................................................");
    }

}
