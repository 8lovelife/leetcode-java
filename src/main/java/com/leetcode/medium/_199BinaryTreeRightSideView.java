package com.leetcode.medium;

import java.util.*;

import com.leetcode.TreeNode;

public class _199BinaryTreeRightSideView {

    public List<Integer> rightSideViewRecursive(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        rightSide(root, 0, ans);
        return ans;
    }

    private void rightSide(TreeNode root, int depth, List<Integer> ans) {
        if (root == null)
            return;
        if (ans.size() == depth) {
            ans.add(root.val);
        }
        rightSide(root.right, depth + 1, ans);
        rightSide(root.left, depth + 1, ans);
    }

    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        Queue<TreeNode> q = new ArrayDeque<>();
        q.offer(root);
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size - 1; i++) {
                TreeNode cur = q.poll();
                if (cur.left != null) {
                    q.offer(cur.left);
                }
                if (cur.right != null) {
                    q.offer(cur.right);
                }
            }

            TreeNode lastNode = q.poll();
            ans.add(lastNode.val);
            if (lastNode.left != null) {
                q.offer(lastNode.left);
            }
            if (lastNode.right != null) {
                q.offer(lastNode.right);
            }

        }

        return ans;
    }
}
