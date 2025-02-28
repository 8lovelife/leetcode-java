package com.leetcode.easy;

import java.util.*;

import com.leetcode.TreeNode;

public class _145BinaryTreePostOrderTraversal {

    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        traversal(root, result);
        return result;
    }

    private void traversal(TreeNode node, List<Integer> result) {
        if (node == null) {
            return;
        }
        traversal(node.left, result);
        traversal(node.right, result);
        result.add(node.val);
    }

    public List<Integer> postorderTraversal2Stack(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Stack<TreeNode> stack = new Stack<>();
        Stack<TreeNode> tmpStack = new Stack<>();
        tmpStack.push(root);
        while (!tmpStack.isEmpty()) {
            TreeNode node = tmpStack.pop();
            stack.push(node);

            TreeNode left = node.left;
            TreeNode right = node.right;
            if (left != null) {
                tmpStack.push(left);
            }
            if (right != null) {
                tmpStack.push(right);
            }
        }

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            result.add(node.val);
        }

        return result;
    }
}
