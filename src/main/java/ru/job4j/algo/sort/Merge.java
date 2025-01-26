package ru.job4j.algo.sort;

import java.util.Arrays;

public class Merge {

    public static int[] mergesort(int[] array) {
        int[] result = array;
        int n = array.length;
        if (n > 1) {
            int[] left = mergesort(Arrays.copyOfRange(array, 0, n / 2));
            int[] right = mergesort(Arrays.copyOfRange(array, n / 2, n));
            result = merge(left, right);
        }
        return result;
    }

    private static int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        int n = 0;
        int l = 0;
        int r = 0;
        while (l < left.length && r < right.length) {
            if (left[l] < right[r]) {
                result[n] = left[l];
                l++;
            } else if (left[l] > right[r]) {
                result[n] = right[r];
                r++;
            } else {
                result[n++] = left[l];
                result[n] = right[r];
                l++;
                r++;
            }
            n++;
        }
        while (r < right.length) {
            result[n] = right[r];
            r++;
            n++;
        }
        while (l < left.length) {
            result[n] = left[l];
            l++;
            n++;
        }
        return result;
    }
}
