package com.leetcode.medium;

public class _53MaxSumSubArray {

    // TLE: n to the power of 2
    public int maxSubArray3(int[] nums) {
        int n = nums.length;
        int maxSum = nums[0];
        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = i; j < n; j++) {
                sum += nums[j];
                maxSum = Math.max(maxSum, sum);
            }
        }
        return maxSum;
    }

    // TLE : n to the power of 3
    public int maxSubArrayCombination(int[] nums) {
        int n = nums.length;
        int maxSum = nums[0];
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int sum = 0;
                for (int k = i; k <= j; k++) {
                    sum += nums[k];
                }
                maxSum = Math.max(maxSum, sum);
            }
        }
        return maxSum;
    }

    // O(N)
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
