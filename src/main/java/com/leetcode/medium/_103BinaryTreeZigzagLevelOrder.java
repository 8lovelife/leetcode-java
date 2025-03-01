package com.leetcode.medium;

import java.util.*;

import com.leetcode.TreeNode;

public class _103BinaryTreeZigzagLevelOrder {

    public List<List<Integer>> zigzagLevelOrderLinkedList(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        Queue<TreeNode> q = new ArrayDeque<>();
        q.add(root);
        boolean zigZag = false;
        while (!q.isEmpty()) {
            int size = q.size();
            LinkedList<Integer> result = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();
                if (zigZag) {
                    result.addFirst(node.val);
                } else {
                    result.add(node.val);
                }
                if (node.left != null) {
                    q.add(node.left);
                }
                if (node.right != null) {
                    q.add(node.right);
                }
            }
            ans.add(result);
            zigZag = !zigZag;
        }
        return ans;
    }

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        Queue<TreeNode> q = new ArrayDeque<>();
        q.add(root);
        boolean zigZag = false;
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
            ans.add(result);
            if (zigZag) {
                Collections.reverse(result);
            }
            zigZag = !zigZag;

        }
        return ans;
    }

}
