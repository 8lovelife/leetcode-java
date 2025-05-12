package com.leetcode.easy;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class _1TwoSum {

    // O N to the power of 2
    public int[] twoSum(int[] nums, int target) {

        int n = nums.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (target == nums[j] + nums[i]) {
                    return new int[] { i, j };
                }
            }
        }
        return new int[0];
    }

    // O(N)
    public static int[] twoSum2(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            int search = target - nums[i];
            Integer matchedIndex = map.get(search);
            if (matchedIndex != null) {
                return new int[] { matchedIndex, i };
            } else {
                map.put(nums[i], i);
            }
        }
        return null;
    }

    public int[] twoSum3(int[] nums, int target) {
        int n = nums.length;
        int[][] valueIndex = new int[n][2];
        for (int i = 0; i < n; i++) {
            valueIndex[i][0] = nums[i];
            valueIndex[i][1] = i;
        }
        Arrays.sort(valueIndex, Comparator.comparingInt(o -> o[0]));
        int l = 0;
        int r = n - 1;
        while (l < r) {
            int result = valueIndex[l][0] + valueIndex[r][0];
            if (result > target) {
                r--;
            } else if (result < target) {
                l++;
            } else {
                return new int[] { valueIndex[l][1], valueIndex[r][1] };
            }
        }
        return new int[0];
    }

}
