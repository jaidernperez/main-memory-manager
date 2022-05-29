package com.usta.mainmemorymanager;

import com.usta.mainmemorymanager.utils.FreeMemoryUtil;
import org.junit.jupiter.api.Test;


public class FreeMemoryBlocksTest {

    @Test
    void verifyFreeBlocks01() {
        FreeMemoryUtil.verifyFreeBlocks(
                new int[]{240, 839, 1544, 1811, 3439, 3508, 3953, 4000},
                new int[]{839, 1544, 1811, 2232, 2232, 2887, 2887, 2947},
                new int[]{240, 2947, 3439, 3508, 3953, 4000}
        );
    }


}
