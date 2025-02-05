package ru.job4j.algo.slidingwindow;

import java.util.ArrayDeque;
import java.util.Deque;

public class SlidingWindowMax {
    /**
     * Вам дан массив целых чисел  nums,
     * есть скользящее окно размером k,
     * которое движется от самого левого угла массива до самого правого.
     * Вы можете видеть только числа kв окне.
     * Каждый раз скользящее окно перемещается вправо на одну позицию.
     * Верните максимальное значение из скользящего окна.
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || k <= 0) {
            return new int[0];
        }
        int n = nums.length;
        int[] result = new int[n - k + 1];
        /**
         * хранилище индексов
         */
        Deque<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < nums.length; i++) {
            /**
             * удаляем числа за пределами диапазона k
             */
            if (!q.isEmpty() && q.peek() < i - k + 1) {
                q.poll();
            }
            /**
             * удаляем меньшие числа в диапазоне k, поскольку они бесполезны
             */
            while (!q.isEmpty() && nums[q.peekLast()] < nums[i]) {
                q.pollLast();
            }
            /**
             * q содержит индекс... в result собираем значения
             */
            q.add(i);
            if (i >= k - 1) {
                result[i - k + 1] = nums[q.peek()];
            }
        }
        return result;
    }
}
