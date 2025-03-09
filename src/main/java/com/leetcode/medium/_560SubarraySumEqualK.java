package com.leetcode.medium;

import java.util.*;

public class _560SubarraySumEqualK {
    public int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> prefixSum = new HashMap<>();
        prefixSum.put(0, 1);
        int[] sum = { 0 };
        Arrays.stream(nums).reduce(0, (u, v) -> {
            int curSum = u + v;

            sum[0] += prefixSum.getOrDefault(curSum - k, 0);

            prefixSum.merge(curSum, 1, Integer::sum);
            return curSum;
        });
        return sum[0];
    }

    public int subarraySumBruteForce(int[] nums, int k) {
        int n = nums.length;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            int cumulativeSum = 0;
            for (int j = i; j < n; j++) {
                cumulativeSum += nums[j];
                if (cumulativeSum == k) {
                    sum++;
                }
            }
        }
        return sum;
    }
}
