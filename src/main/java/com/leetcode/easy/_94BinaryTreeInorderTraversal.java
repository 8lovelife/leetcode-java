package com.leetcode.easy;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import com.leetcode.TreeNode;

public class _94BinaryTreeInorderTraversal {

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        traversal(root, result);
        return result;
    }

    private void traversal(TreeNode root, List<Integer> result) {
        if (root == null) {
            return;
        }
        traversal(root.left, result);
        result.add(root.val);
        traversal(root.right, result);
    }

    public List<Integer> inorderTraversalStack(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode tmp = root;
        while (!stack.isEmpty() || tmp != null) {
            while (tmp != null) {
                stack.push(tmp);
                tmp = tmp.left;
            }
            TreeNode node = stack.pop();
            result.add(node.val);
            tmp = node.right;
        }
        return result;
    }
}