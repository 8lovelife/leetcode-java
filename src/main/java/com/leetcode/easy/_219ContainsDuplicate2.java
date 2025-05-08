package com.leetcode.easy;

import java.util.HashSet;
import java.util.Set;

public class _219ContainsDuplicate2 {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Set<Integer> sets = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (i > k) {
                sets.remove(nums[i - k - 1]);
            }
            if (!sets.add(nums[i])) {
                return true;
            }
        }
        return false;
    }
}
