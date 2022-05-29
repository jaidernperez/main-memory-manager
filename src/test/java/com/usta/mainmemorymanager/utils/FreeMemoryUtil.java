package com.usta.mainmemorymanager.utils;

import com.usta.mainmemorymanager.models.FreeBlock;
import com.usta.mainmemorymanager.models.FreeMemory;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.List;

public class FreeMemoryUtil {

    public static void verifyFreeBlocks(int[] base, int[] add, int[] expected) {
        FreeMemory freeMemory = new FreeMemory(1);

        freeMemory.setFreeBlocksMemory(buildBlocks(base));
        buildBlocks(add).forEach(freeMemory::addFreeBlockMemory);
        List<FreeBlock> freeBlocksExpected = buildBlocks(expected);

        Assertions.assertEquals(freeMemory.getFreeBlocksMemory(), freeBlocksExpected);
    }

    private static List<FreeBlock> buildBlocks(int[] numbers) {
        List<FreeBlock> freeBlocks = new ArrayList<>();
        for (int i = 0; i < numbers.length; i += 2) {
            freeBlocks.add(new FreeBlock(numbers[i], numbers[i + 1]));
        }
        return freeBlocks;
    }

}
