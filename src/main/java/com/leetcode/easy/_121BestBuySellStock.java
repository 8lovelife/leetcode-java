package com.leetcode.easy;

public class _121BestBuySellStock {

    public int maxProfit2(int[] prices) {
        int miniBuy = Integer.MAX_VALUE;
        int maxProfit = 0;
        for (int price : prices) {
            miniBuy = Math.min(miniBuy, price);
            maxProfit = Math.max(price - miniBuy, maxProfit);
        }
        return maxProfit;
    }

    public int maxProfit(int[] prices) {
        int maxProfit = 0;
        int miniBuy = Integer.MAX_VALUE;
        for (int price : prices) {
            if (price < miniBuy) {
                miniBuy = price;
            } else {
                maxProfit = Math.max(maxProfit, (price - miniBuy));
            }
        }
        return maxProfit;
    }
}
