package com.leetcode.easy;

import com.leetcode.TreeNode;

public class _112PathSum {

    public boolean hasPathSumLessCode(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null) {
            return root.val == targetSum;
        }

        return hasPathSumLessCode(root.left, targetSum - root.val)
                || hasPathSumLessCode(root.right, targetSum - root.val);
    }

    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }
        return pathSum(root, 0, targetSum);
    }

    private boolean pathSum(TreeNode root, int curSum, int targetSum) {
        if (root == null)
            return false;

        // if (curSum > targetSum) {
        // return false;
        // }

        curSum += root.val;
        if (root.right == null && root.left == null) {
            return curSum == targetSum;
        }

        boolean leftResult = pathSum(root.left, curSum, targetSum);
        boolean rightResult = pathSum(root.right, curSum, targetSum);

        return leftResult || rightResult;

    }
}
