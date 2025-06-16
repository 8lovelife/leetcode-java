package com.leetcode.medium;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

public class _322CoinChange {

    public int coinChangeBFS(int[] coins, int amount) {

        if (amount == 0) {
            return 0;
        }

        Queue<Integer> q = new ArrayDeque<>();
        Set<Integer> visited = new HashSet<>();
        q.offer(0);
        visited.add(0);

        int steps = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            steps++;
            for (int i = 0; i < size; i++) {
                int curAmount = q.poll();
                for (int coin : coins) {
                    int nextAmount = curAmount + coin;
                    if (nextAmount == amount) {
                        return steps;
                    }
                    if (nextAmount < amount && visited.add(nextAmount)) {
                        q.offer(nextAmount);
                    }
                }
            }
        }
        return -1;
    }

    public int coinChangeBottomUp(int[] coins, int amount) {
        int max = amount + 1;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, max);
        dp[0] = 0;

        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (i - coin >= 0) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }

        return dp[amount] == max ? -1 : dp[amount];
    }

    public int coinChangeTopDown(int[] coins, int amount) {
        int[] memo = new int[amount + 1];
        return dfs(coins, amount, memo);
    }

    private int dfs(int[] coins, int amount, int[] memo) {
        if (amount < 0) {
            return -1;
        }
        if (amount == 0) {
            return 0;
        }
        if (memo[amount] != 0) {
            return memo[amount];
        }

        int minCoins = Integer.MAX_VALUE;
        for (int coin : coins) {
            int res = dfs(coins, amount - coin, memo);
            if (res >= 0) {
                minCoins = Math.min(minCoins, res + 1);
            }
        }
        memo[amount] = minCoins == Integer.MAX_VALUE ? -1 : minCoins;
        return memo[amount];
    }
}
