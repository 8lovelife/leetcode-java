package com.leetcode.medium;

import java.util.*;

import com.leetcode.TreeNode;

public class _113PathSum2 {
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> result = new ArrayList<>();
        pathSum(root, result, new ArrayList<>());
        result.removeIf(list -> list.stream().mapToInt(Integer::intValue).sum() != targetSum);
        return result;
    }

    private void pathSum(TreeNode root, List<List<Integer>> result, List<Integer> path) {
        if (root == null) {
            return;
        }
        path.add(root.val);
        if (root.left == null && root.right == null) {
            result.add(new ArrayList<>(path));
        } else {
            pathSum(root.left, result, path);
            pathSum(root.right, result, path);
        }
        path.remove(path.size() - 1);
    }

    public List<List<Integer>> pathSumOptimization(TreeNode root, int targetSum) {
        List<List<Integer>> result = new ArrayList<>();
        pathSumOptimization(root, targetSum, result, new ArrayList<>());
        // result.removeIf(list -> list.stream().mapToInt(Integer::intValue).sum() !=
        // targetSum);
        return result;
    }

    private void pathSumOptimization(TreeNode root, int targetSum, List<List<Integer>> result, List<Integer> path) {
        if (root == null) {
            return;
        }
        path.add(root.val);
        if (root.left == null && root.right == null && root.val == targetSum) {
            result.add(new ArrayList<>(path));
        } else {
            pathSumOptimization(root.left, targetSum - root.val, result, path);
            pathSumOptimization(root.right, targetSum - root.val, result, path);
        }
        path.remove(path.size() - 1);
    }
}
