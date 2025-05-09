package com.leetcode.medium;

public class _53MaxSumSubArray {
    public int maxSubArray(int[] nums) {
        int curMaxSum = nums[0];
        int maxSum = curMaxSum;
        for (int i = 1; i < nums.length; i++) {
            curMaxSum = Math.max(curMaxSum + nums[i], nums[i]);
            maxSum = Math.max(maxSum, curMaxSum);
        }
        return maxSum;
    }
}
