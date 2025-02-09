package ru.job4j.algo.greedy;

public class BestTimeBuySellStock {
    /**
     * Вы хотите максимизировать свою прибыль,
     * выбрав один день для покупки одних акций и другой день в будущем для продажи этих акций.
     * Верните максимальную прибыль, которую вы можете получить от этой транзакции.
     * Если вы не можете получить никакой прибыли, верните 0.
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int buy = prices[0];
        int profit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] < buy) {
                buy = prices[i];
            } else if (prices[i] - buy > profit) {
                profit = prices[i] - buy;
            }
        }
        return profit;
    }
}
