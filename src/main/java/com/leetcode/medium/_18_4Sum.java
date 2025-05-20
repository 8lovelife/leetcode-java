package com.leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _18_4Sum {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        int n = nums.length;
        if (n < 4) {
            return ans;
        }

        Arrays.sort(nums);

        for (int i = 0; i < n; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            long _3Sum = target - nums[i];
            for (int k = i + 1; k < n; k++) {
                if (k > i + 1 && nums[k] == nums[k - 1]) {
                    continue;
                }

                int l = k + 1;
                int r = n - 1;
                while (l < r) {
                    long sums = (long) nums[k] + nums[l] + nums[r];
                    if (sums > _3Sum) {
                        r--;
                    } else if (sums < _3Sum) {
                        l++;
                    } else {
                        ans.add(Arrays.asList(nums[i], nums[k], nums[l], nums[r]));
                        l++;
                        while (l < r && nums[l - 1] == nums[l]) {
                            l++;
                        }
                    }
                }
            }
        }

        return ans;

    }
}
