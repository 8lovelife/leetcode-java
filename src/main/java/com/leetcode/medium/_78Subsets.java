package com.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

public class _78Subsets {

    public List<List<Integer>> subsetsBinaryStringWithTrick(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        int n = nums.length;
        int nthBit = 1 << n;
        for (int i = 0; i < nthBit; i++) {
            List<Integer> result = new ArrayList<>();
            String binaryString = Integer.toBinaryString(i | nthBit).substring(1);
            for (int j = 0; j < n; j++) {
                if (binaryString.charAt(j) == '1') {
                    result.add(nums[j]);
                }
            }
            ans.add(result);
        }
        return ans;
    }

    public List<List<Integer>> subsetsBinaryString(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        int n = nums.length;
        int start = (int) Math.pow(2, n);
        int end = (int) Math.pow(2, n + 1);
        for (int i = start; i < end; i++) {
            List<Integer> result = new ArrayList<>();
            String binaryString = Integer.toBinaryString(i).substring(1);
            for (int j = 0; j < n; j++) {
                if (binaryString.charAt(j) == '1') {
                    result.add(nums[j]);
                }
            }
            ans.add(result);
        }
        return ans;
    }

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        subsets(0, nums, new ArrayList<>(), ans);
        return ans;
    }

    private void subsets(int currentIndex, int[] nums, List<Integer> result, List<List<Integer>> ans) {
        ans.add(new ArrayList<>(result));
        for (int i = currentIndex; i < nums.length; i++) {
            result.add(nums[i]);
            subsets(i + 1, nums, result, ans);
            result.remove(result.size() - 1);
        }
    }

}
