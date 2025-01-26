package ru.job4j.algo;

import java.util.Arrays;
import java.util.Comparator;

class IntervalMerger {
    /**
     * Time O(n) сортируется не массив примитивов, а массив объектов.
     * Space O(nLog(n)) в java используется сортировка слиянием.
     * @param intervals unsorted intervals
     * @return merged intervals
     */
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals , Comparator.comparingInt(a -> a[0]));
        int index = 0;
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] <= intervals[index][1]) {
                intervals[index][1] = Math.max(intervals[index][1], intervals[i][1]);
            } else {
                index++;
                intervals[index] = intervals[i];
            }
        }
        return Arrays.copyOf(intervals, index + 1);
    }
}