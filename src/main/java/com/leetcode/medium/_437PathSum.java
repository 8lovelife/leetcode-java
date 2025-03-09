package com.leetcode.medium;

import java.util.*;

import com.leetcode.TreeNode;

public class _437PathSum {

    public int pathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return 0;
        }
        int pathSums = 0;
        Queue<TreeNode> q = new ArrayDeque<>();
        q.offer(root);
        while (!q.isEmpty()) {
            TreeNode cur = q.poll();
            if (cur.left != null) {
                q.offer(cur.left);
            }
            if (cur.right != null) {
                q.offer(cur.right);
            }
            int[] pathSum = new int[1];
            pathSum(cur, 0L, targetSum, pathSum);
            pathSums += pathSum[0];
        }
        return pathSums;
    }

    private void pathSum(TreeNode node, Long curSum, int targetSum, int[] pathSum) {
        if (node == null) {
            return;
        }

        curSum += node.val;
        if (curSum == targetSum) {
            pathSum[0] += 1;
        }
        pathSum(node.left, curSum, targetSum, pathSum);
        pathSum(node.right, curSum, targetSum, pathSum);

    }
}
