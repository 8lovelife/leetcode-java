package com.leetcode.medium;

import java.util.*;

import com.leetcode.TreeNode;

public class _107BinaryTreeLevelTraversal {

    public List<List<Integer>> levelOrderBottomRecursive(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        levelOrderBottom(root, 0, ans);
        Collections.reverse(ans);
        return ans;
    }

    private void levelOrderBottom(TreeNode node, int depth, List<List<Integer>> ans) {
        if (ans.size() <= depth) {
            ans.add(new ArrayList<>());
        }
        ans.get(depth).add(node.val);
        if (node.left != null) {
            levelOrderBottom(node.left, depth + 1, ans);
        }
        if (node.right != null) {
            levelOrderBottom(node.right, depth + 1, ans);
        }
    }

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        LinkedList<List<Integer>> ans = new LinkedList<>();
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
                if (node.left != null) {
                    q.add(node.left);
                }
                if (node.right != null) {
                    q.add(node.right);
                }
            }
            ans.addFirst(result);
        }
        return ans;
    }
}
