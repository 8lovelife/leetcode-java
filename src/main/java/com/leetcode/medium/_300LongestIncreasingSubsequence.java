package com.leetcode.medium;

import java.util.Arrays;

public class _300LongestIncreasingSubsequence {

    public int lengthOfLIS2(int[] nums) {
        int n = nums.length;
        int[] tails = new int[n];
        int size = 0;
        for (int num : nums) {
            int index = Arrays.binarySearch(tails, 0, size, num);
            if (index < 0) {
                index = -index - 1;
            }
            tails[index] = num;
            if (index == size) {
                size++;
            }
        }
        return size;
    }

    // n squared
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        int maxLength = 1;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            maxLength = Math.max(maxLength, dp[i]);
        }
        return maxLength;
    }
}
