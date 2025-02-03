package ru.job4j.algo.twopointers;

public class ContainerWithWater {
    /**
     * Вам дан целочисленный массив height длины n.
     * Нарисованы n вертикальные линии,
     * две конечные точки которых — и .ith(i, 0)(i, height[i])
     * Найдите две линии,
     * которые вместе с осью x образуют емкость,
     * такую, чтобы емкость содержала наибольшее количество воды.
     * @param height
     * @return
     */
    public int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int area = 0;
        while (left < right) {
            int weight = right - left;
            if (height[left] < height[right]) {
                area = Math.max(area, weight * height[left]);
                left++;
            } else {
                area = Math.max(area, weight * height[right]);
                right--;
            }
        }
        return area;
    }
}
