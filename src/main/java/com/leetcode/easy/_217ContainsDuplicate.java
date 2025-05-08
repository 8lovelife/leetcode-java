package com.leetcode.easy;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class _217ContainsDuplicate {

    public boolean containsDuplicate2(int[] nums) {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == nums[i + 1]) {
                return true;
            }
        }
        return false;
    }

    public boolean containsDuplicate(int[] nums) {
        Set<Integer> sets = new HashSet<>(nums.length);
        for (int num : nums) {
            if (!sets.add(num)) {
                return true;
            }
        }
        return false;
    }
}
