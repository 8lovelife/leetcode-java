package com.leetcode.medium;

import java.util.*;

import com.leetcode.TreeNode;

public class _102BinaryTreeLevelOrderTraversal {

    public List<List<Integer>> levelOrderRecursive(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        recursiveTraversal(root, 0, ans);
        return ans;
    }

    private void recursiveTraversal(TreeNode node, int depth, List<List<Integer>> ans) {
        if (ans.size() <= depth) {
            ans.add(new ArrayList<>());
        }

        List<Integer> result = ans.get(depth);
        result.add(node.val);

        if (node.left != null) {
            recursiveTraversal(node.left, depth + 1, ans);
        }
        if (node.right != null) {
            recursiveTraversal(node.right, depth + 1, ans);
        }
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        Queue<TreeNode> q = new ArrayDeque<>();
        q.add(root);
        while (!q.isEmpty()) {
            int size = q.size();
            List<Integer> result = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();
                result.add(node.val);
                if (node.left != null)
                    q.add(node.left);
                if (node.right != null)
                    q.add(node.right);
            }
            ans.add(result);
        }
        return ans;
    }
}
