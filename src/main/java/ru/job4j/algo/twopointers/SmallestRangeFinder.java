package ru.job4j.algo.twopointers;

import java.util.*;

public class SmallestRangeFinder {

    /**
     * Метод должен принимать массив nums и целочисленное значение k,
     * и возвращать массив из двух целых чисел, представляющих наименьший диапазон
     * @param nums
     * @param k
     * @return
     */
    public static int[] findSmallestRange(int[] nums, int k) {
        int left = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i - 1] == nums[i]) {
                left = i;
            }
            if (i - left == k - 1) {
                return new int[] {left, i};
            }
        }
        return null;
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, 5, 7, 9};
        int k = 3;
        int[] result = findSmallestRange(nums, k);
        if (result != null) {
            System.out.println("Наименьший диапазон с " + k + " различными элементами: " + Arrays.toString(result));
        } else {
            System.out.println("Такой диапазон не существует.");
        }
    }
}
