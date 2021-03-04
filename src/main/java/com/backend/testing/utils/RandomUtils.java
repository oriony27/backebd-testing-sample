package com.backend.testing.utils;

import java.util.Random;

public final class RandomUtils {
    private static Random rand = new Random();

    private RandomUtils() {
        throw new IllegalStateException("This is utility class!");
    }

    public static int getRandomNumberLessThen(int maxValue){
        int minValue = 1;
        return rand.nextInt((maxValue - minValue + 1)) + minValue;
    }
}
