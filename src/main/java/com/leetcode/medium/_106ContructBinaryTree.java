package com.leetcode.medium;

import java.util.*;

import com.leetcode.TreeNode;

public class _106ContructBinaryTree {

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        Map<Integer, Integer> inorderIndexs = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorderIndexs.put(inorder[i], i);
        }
        TreeNode root = buildTree(postorder.length - 1, postorder, 0, postorder.length - 1, inorderIndexs);
        return root;
    }

    private TreeNode buildTree(int postIndex, int[] postorder, int left, int right,
            Map<Integer, Integer> inorderIndexs) {
        if (left > right) {
            return null;
        }
        int val = postorder[postIndex];
        TreeNode root = new TreeNode(val);
        int inorderIndex = inorderIndexs.get(val);

        root.right = buildTree(postIndex - 1, postorder, inorderIndex + 1, right, inorderIndexs);
        root.left = buildTree(postIndex - 1 - (right - inorderIndex), postorder, left, inorderIndex - 1, inorderIndexs);
        return root;
    }

}
