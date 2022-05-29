package com.usta.mainmemorymanager.models;

import lombok.Data;

import java.util.*;
import java.util.stream.Collectors;

import static com.usta.mainmemorymanager.utils.Constants.OS_SIZE;

@Data
public class FreeMemory {

    private List<FreeBlock> freeBlocksMemory;

    public FreeMemory(int sizeMemory) {
        freeBlocksMemory = new ArrayList<>();
        freeBlocksMemory.add(new FreeBlock(OS_SIZE, sizeMemory));
    }

    public void addFreeBlockMemory(FreeBlock freeBlock) {
        freeBlocksMemory.add(freeBlock);
        updateFreeMemory();
    }

    private void updateFreeMemory() {
        List<FreeBlock> freeBlocks = new ArrayList<>();
        freeBlocksMemory = freeBlocksMemory.stream()
                .sorted(Comparator.comparing(FreeBlock::getStartLocation)).collect(Collectors.toList());
        FreeBlock block =  freeBlocksMemory.get(0);
        for (int i = 1; i < freeBlocksMemory.size(); i++) {
            if (block.getEndLocation() == freeBlocksMemory.get(i).getStartLocation()) {
                block.setEndLocation(freeBlocksMemory.get(i).getEndLocation());
            } else {
                freeBlocks.add(block);
                block = freeBlocksMemory.get(i);
            }
        }
        freeBlocks.add(block);
        freeBlocksMemory = freeBlocks;
    }

    public List<FreeBlock> getSortedBlocksBySize() {
        List<FreeBlock> sortedFreeBlocksByTotalSize = freeBlocksMemory.stream()
                .sorted(Comparator.comparing(FreeBlock::getTotalSize)).collect(Collectors.toList());
        Collections.reverse(sortedFreeBlocksByTotalSize);
        return sortedFreeBlocksByTotalSize;
    }

    private List<FreeBlock> cloneList(List<FreeBlock> list) {
        List<FreeBlock> listCloned = new ArrayList<>();
        for (FreeBlock block: list) {
            listCloned.add(new FreeBlock(block.getStartLocation(), block.getEndLocation()));
        }
        return listCloned;
    }
}
