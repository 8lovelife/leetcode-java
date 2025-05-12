package com.leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class _15_3Sum {

    // Big O of n to the power of 3
    public List<List<Integer>> threeSumBacktrack(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> combos = new ArrayList<>();
        dfs(0, 0, nums, ans, combos);
        return ans;
    }

    private void dfs(int currentIndex, int currentTarget, int[] nums, List<List<Integer>> ans, List<Integer> combos) {

        if (combos.size() == 3 && currentTarget == 0) {
            ans.add(new ArrayList<>(combos));
            return;
        }

        if (combos.size() == 3) {
            return;
        }

        for (int i = currentIndex; i < nums.length; i++) {
            if (i > currentIndex && nums[i] == nums[i - 1]) {
                continue;
            }
            combos.add(nums[i]);
            int target = nums[i] + currentTarget;
            dfs(i + 1, target, nums, ans, combos);
            combos.remove(combos.size() - 1);
        }
    }

    // two pointer Big O of n squared
    public List<List<Integer>> threeSum3(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        int n = nums.length;
        Arrays.sort(nums);
        for (int i = 0; i < n; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int l = i + 1;
            int r = n - 1;
            while (l < r) {
                int target = nums[i] + nums[l] + nums[r];
                if (target > 0) {
                    r--;
                } else if (target < 0) {
                    l++;
                } else {
                    ans.add(Arrays.asList(nums[i], nums[l], nums[r]));
                    l++;
                    while (l < r && nums[l - 1] == nums[l]) {
                        l++;
                    }
                }
            }
        }
        return ans;
    }

    // Big O of n to the power of 2 | Big O of n squared
    public List<List<Integer>> threeSum2(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        int n = nums.length;
        Arrays.sort(nums);
        for (int i = 0; i < n; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int _2SumTarget = 0 - nums[i];
            Set<Integer> searchSet = new HashSet<>();
            Set<Integer> seenSet = new HashSet<>();
            for (int j = i + 1; j < n; j++) {
                int target = _2SumTarget - nums[j];
                if (searchSet.contains(target) && seenSet.add(nums[j])) {
                    ans.add(Arrays.asList(nums[i], nums[j], target));
                } else {
                    searchSet.add(nums[j]);
                }
            }
        }
        return ans;
    }

    // TLE
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        int n = nums.length;
        Arrays.sort(nums);
        for (int i = 0; i < n; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            for (int j = i + 1; j < n; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                for (int k = j + 1; k < n; k++) {
                    if (k > j + 1 && nums[k] == nums[k - 1]) {
                        continue;
                    }
                    int target = nums[i] + nums[j] + nums[k];
                    if (target == 0) {
                        ans.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    }
                }
            }
        }
        return ans;
    }

}
