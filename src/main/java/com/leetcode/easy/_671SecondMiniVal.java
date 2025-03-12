package com.leetcode.easy;

import com.leetcode.TreeNode;

import java.util.ArrayDeque;
import java.util.Queue;

public class _671SecondMiniVal {
    public int findSecondMinimumValue(TreeNode root) {
        if (root == null || root.left == null || root.right == null) {
            return -1;
        }

        int leftVal = root.left.val;
        int rightVal = root.right.val;

        if (leftVal == root.val) {
            leftVal = findSecondMinimumValue(root.left);
        }
        if (rightVal == root.val) {
            rightVal = findSecondMinimumValue(root.right);
        }

        if (leftVal == -1) {
            return rightVal;
        }
        if (rightVal == -1) {
            return leftVal;
        }

        return Math.min(leftVal, rightVal);

    }

    public int findSecondMinimumValueIterate(TreeNode root) {
        Queue<TreeNode> q = new ArrayDeque<>();
        q.offer(root);
        int miniVal = root.val;
        int secondMiniVal = Integer.MAX_VALUE;
        boolean foundSecondMiniVal = false;
        while (!q.isEmpty()) {
            TreeNode curNode = q.poll();
            if (curNode.val > miniVal && curNode.val <= secondMiniVal) {
                foundSecondMiniVal = true;
                secondMiniVal = curNode.val;
            }

            if (curNode.left != null) {
                q.offer(curNode.left);
                q.offer(curNode.right);
            }

        }

        return foundSecondMiniVal ? secondMiniVal : -1;

    }
}