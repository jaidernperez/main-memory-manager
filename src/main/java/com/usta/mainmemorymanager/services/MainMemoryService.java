package com.usta.mainmemorymanager.services;

import com.usta.mainmemorymanager.entitites.FreeBlock;
import com.usta.mainmemorymanager.entitites.Process;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MainMemoryService {

    private final Queue<Process> waitingProcess = new LinkedList<>();
    private final List<Process> runningProcess = new ArrayList<>();
    private final List<FreeBlock> freeMemorySpaces = new ArrayList<>();

    public MainMemoryService() {
        this.freeMemorySpaces.add(new FreeBlock(0, 4098, 4098));
    }

    public void addProcess(Process process) {
        waitingProcess.add(process);
    }

    public void executeProcess() {
        Process process = waitingProcess.poll();
        FreeBlock freeBlock = verifySpace();
        assert process != null;
        if (process.getSize() > freeBlock.getSpace()) {
            System.out.println("cannot add process space full");
            return;
        }
        process.setStartLocation(freeBlock.getStartLocation());
        process.setEndLocation(process.getStartLocation() + process.getSize());
        runningProcess.add(process);
        freeBlock.setStartLocation(freeBlock.getStartLocation() + process.getSize());
        freeBlock.setSpace(freeBlock.getSpace() - process.getSize());

    }

    public FreeBlock verifySpace() {
        FreeBlock moreSpaceBlock = new FreeBlock();
        moreSpaceBlock.setSpace(0);

        for (FreeBlock block : freeMemorySpaces) {
            if (block.getSpace() > moreSpaceBlock.getSpace()) {
                moreSpaceBlock = block;
            }
        }
        return moreSpaceBlock;
    }

    public void removeProcess(Process process) {
        runningProcess.remove(process);
        freeMemorySpaces.add(new FreeBlock(process.getStartLocation(),
                process.getEndLocation(), process.getSize()));
        unifyBlocks();
    }

    public void unifyBlocks() {
        freeMemorySpaces.sort(Comparator.comparing(FreeBlock::getStartLocation));
        FreeBlock freeBlock = freeMemorySpaces.get(0);
        List<FreeBlock> oldBlocks = new ArrayList<>();
        List<FreeBlock> newBlocks = new ArrayList<>();
        for (FreeBlock block : freeMemorySpaces) {
            if (freeBlock != block && freeBlock.getEndLocation() == block.getStartLocation()) {
                newBlocks.add(new FreeBlock(freeBlock.getStartLocation(),
                        block.getEndLocation(),
                        (block.getEndLocation() - freeBlock.getStartLocation())));
                oldBlocks.add(freeBlock);
                oldBlocks.add(block);
            }
            freeBlock=block;
        }
        if (!newBlocks.isEmpty()) {
            freeMemorySpaces.addAll(newBlocks);
            freeMemorySpaces.removeAll(oldBlocks);
        }
    }


}
