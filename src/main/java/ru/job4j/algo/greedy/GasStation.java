package ru.job4j.algo.greedy;

public class GasStation {
    /**
     * Сложность O(n)
     * Доп. память O(1)
     * @param gas
     * @param cost
     * @return
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int totalGus = 0;
        int totalCost = 0;
        int tank = 0;
        int start = 0;
        for (int i = 0; i < gas.length; i++) {
            totalGus += gas[i];
            totalCost += cost[i];
            tank += gas[i] - cost[i];
            if (tank < 0) {
                start = i + 1;
                tank = 0;
            }
        }
        if (totalGus < totalCost) {
            return -1;
        }
        return start;
    }
}
