package com.leetcode.medium;

import java.util.Arrays;

public class _198HouseRobber {

    // two to the power of n
    public int rob(int[] nums) {
        return dfs(nums, 0);
    }

    private int dfs(int[] nums, int curIndex) {
        if (curIndex >= nums.length) {
            return 0;
        }
        return Math.max(dfs(nums, curIndex + 1),
                dfs(nums, curIndex + 2) + nums[curIndex]);
    }

    // top-down O(N)
    public int robWithMemory(int[] nums) {
        int[] robbed = new int[nums.length];
        Arrays.fill(robbed, -1);
        return dfsWithMemory(nums, 0, robbed);
    }

    private int dfsWithMemory(int[] nums, int curIndex, int[] robbed) {
        if (curIndex >= nums.length) {
            return 0;
        }
        if (robbed[curIndex] != -1) {
            return robbed[curIndex];
        }
        int totalAmount = Math.max(dfsWithMemory(nums, curIndex + 1, robbed),
                dfsWithMemory(nums, curIndex + 2, robbed) + nums[curIndex]);
        robbed[curIndex] = totalAmount;
        return totalAmount;
    }

    // bottom up O(N)
    public int robBottomUp(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(dp[0], nums[1]);
        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }
        return dp[n - 1];
    }

}
