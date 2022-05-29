package com.usta.mainmemorymanager.utils;

import java.util.Random;
import java.util.UUID;

public class RandomUtil {

    public static String generateId() {
        return UUID.randomUUID().toString();
    }

    public static int generateRandomNumber(int min, int max) {
        return new Random().nextInt(max - min) + min;
    }
}
