package com.leetcode.medium;

import java.util.Arrays;

public class _2090KRadiusSubarrayAvg {

    public int[] getAverages3(int[] nums, int k) {
        int n = nums.length;
        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        int windows = 2 * k + 1;
        if (windows > n) {
            return ans;
        }

        long preSum = 0;
        for (int i = 0; i < windows; i++) {
            preSum += nums[i];
        }
        ans[k] = (int) (preSum / windows);

        for (int j = windows; j < n; j++) {
            preSum += nums[j] - nums[j - windows];
            ans[j - k] = (int) (preSum / windows);
        }

        return ans;
    }

    public int[] getAverages2(int[] nums, int k) {
        int n = nums.length;
        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        int windows = 2 * k + 1;
        if (windows > n) {
            return ans;
        }

        long preSum = 0;
        for (int i = 0; i < windows; i++) {
            preSum += nums[i];
        }
        ans[k] = (int) (preSum / windows);

        for (int j = k + 1; j < n - k; j++) {
            preSum += nums[j + k] - nums[j - k - 1];
            ans[j] = (int) (preSum / windows);
        }

        return ans;
    }

    public int[] getAverages(int[] nums, int k) {

        int n = nums.length;
        int[] result = new int[n];
        long preSum = 0;
        for (int i = 0; i < n; i++) {
            int beforeRadius = i - k;
            int afterRadius = i + k;
            if (beforeRadius < 0 || afterRadius >= n) {
                preSum += nums[i];
                result[i] = -1;
                continue;
            }

            if (beforeRadius == 0) {
                for (int j = k; j <= k + k; j++) {
                    preSum += nums[j];
                }
                result[i] = (int) (preSum / (2 * k + 1));
                continue;
            }

            preSum = preSum - nums[beforeRadius - 1] + nums[afterRadius];

            result[i] = (int) (preSum / (2 * k + 1));

        }

        return result;
    }
}
