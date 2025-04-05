package com.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

public class _46Permutations {

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        boolean[] visited = new boolean[nums.length];
        permute(nums, visited, new ArrayList<>(), result);
        return result;
    }

    private void permute(int[] nums, boolean[] visited, List<Integer> permutation, List<List<Integer>> result) {
        if (permutation.size() == nums.length) {
            result.add(new ArrayList<>(permutation));
            return;
        }

        for (int i = 0; i < nums.length; i++) {

            if (visited[i]) {
                continue;
            }
            visited[i] = true;
            permutation.add(nums[i]);
            permute(nums, visited, permutation, result);
            permutation.remove(permutation.size() - 1);
            visited[i] = false;
        }
    }

    public List<List<Integer>> permuteSwap(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        permute(nums, 0, new ArrayList<>(), result);
        return result;
    }

    private void permute(int[] nums, int start, List<Integer> permutation, List<List<Integer>> result) {
        if (start == nums.length) {
            List<Integer> perm = new ArrayList<>(start);
            for (int num : nums) {
                perm.add(num);
            }
            result.add(perm);
            return;
        }

        for (int i = start; i < nums.length; i++) {
            swap(nums, start, i);
            permute(nums, start + 1, permutation, result);
            swap(nums, start, i);
        }
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
