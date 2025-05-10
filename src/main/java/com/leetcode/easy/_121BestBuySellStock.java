package com.leetcode.easy;

public class _121BestBuySellStock {

    public int maxProfitAndPrintBuySellDays(int[] prices) {
        int miniBuy = Integer.MAX_VALUE;
        int maxProfit = 0;
        int sellDay = -1;
        int buyDay = -1;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < miniBuy) {
                miniBuy = prices[i];
                buyDay = i;
            }
            int curProfit = prices[i] - miniBuy;
            if (curProfit > maxProfit) {
                maxProfit = curProfit;
                sellDay = i;
            }
        }
        System.out.println("Buy Day " + buyDay + " Buy Price " + prices[sellDay]);
        System.out.println("Sell Day " + sellDay + " Sell Price " + prices[sellDay]);
        return maxProfit;
    }

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
