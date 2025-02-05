package ru.job4j.algo.slidingwindow;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

class Interval {
    int start;
    int end;

    Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return String.format("[%s, %s]", start, end);
    }
}

public class Main {
    /**
     * Доп. память O(n) в худшем случае если все елементы будут помещены в активные интервалы.
     * Сложность выполнения O(nLog(n)) перебор всех элементов и иx добавление каждого в очередь в худшем случае.
     * @param intervals
     * @return
     */
    public static int[] findMaxOverlapInterval(List<Interval> intervals) {
        if (intervals.isEmpty()) {
            return new int[] {-1, -1};
        }

        /**
         * Сортируем интервалы по началу
         */
        intervals.sort(Comparator.comparingInt(i -> i.start));

        /**
         * Очередь для хранения активных интервалов
         */
        var activeIntervals = new PriorityQueue<Interval>(Comparator.comparingInt(i -> i.end));
        int maxOverlap = 0;
        int maxStart = -1;
        int maxEnd = -1;

        for (int i = 0; i < intervals.size(); i++) {
            /**
             * Удаляем интервалы, которые больше не пересекаются с текущим
             */
            while (!activeIntervals.isEmpty() && activeIntervals.peek().end < intervals.get(i).start) {
                activeIntervals.poll();
            }
            /**
             * Добавляем текущий интервал в активные
             */
            activeIntervals.add(intervals.get(i));
            /**
             * Проверяем, если текущее перекрытие больше максимального
             */
            if (activeIntervals.size() > maxOverlap) {
                maxOverlap = activeIntervals.size();
                if (intervals.get(i).start == activeIntervals.peek().end) {
                    /**
                     * интервал это промежуток: от - включительно, до - исключительно.
                     * в ситуации соприкасания возвращаемся в обоих направлениях на шаг назад.
                     */
                    maxStart = intervals.get(i - 1).start;
                    Interval last = activeIntervals.poll();
                    maxEnd = activeIntervals.peek().end;
                    activeIntervals.add(last);
                } else {
                    maxStart = intervals.get(i).start;
                    maxEnd = activeIntervals.peek().end;
                }

            }
        } return new int[] {maxStart, maxEnd};
    }


    public static void main(String[] args) {
        List intervals = new ArrayList<>();
        intervals.add(new Interval(1, 4));
        intervals.add(new Interval(2, 6));
        intervals.add(new Interval(3, 5));
        intervals.add(new Interval(7, 8));

        int[] result = findMaxOverlapInterval(intervals);

        System.out.println("Interval that overlaps the maximum number of intervals: [" + result[0] + ", " + result[1] + "]");
    }
}
