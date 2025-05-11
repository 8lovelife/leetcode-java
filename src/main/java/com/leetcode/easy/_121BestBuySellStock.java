package com.leetcode.easy;

public class _121BestBuySellStock {

    public int maxProfitAndPrintBuySellDays(int[] prices) {
        int miniBuy = Integer.MAX_VALUE;
        int miniPriceDay = -1;
        int maxProfit = 0;
        int sellDay = -1;
        int buyDay = -1;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < miniBuy) {
                miniBuy = prices[i];
                miniPriceDay = i;
            }
            int curProfit = prices[i] - miniBuy;
            if (curProfit > maxProfit) {
                maxProfit = curProfit;
                buyDay = miniPriceDay;
                sellDay = i;
            }
        }

        if (maxProfit > 0) {
            System.out.println("Buy Day " + (buyDay + 1) + " Buy Price " + prices[buyDay]);
            System.out.println("Sell Day " + (sellDay + 1) + " Sell Price " + prices[sellDay]);
            System.out.println("Max Profit " + maxProfit);
        }

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
