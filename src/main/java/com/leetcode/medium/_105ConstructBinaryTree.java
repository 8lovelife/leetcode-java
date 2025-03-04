package com.leetcode.medium;

import java.util.*;

import com.leetcode.TreeNode;

public class _105ConstructBinaryTree {

    private int preIndex;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        Map<Integer, Integer> inorderSeqIndex = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorderSeqIndex.put(inorder[i], i);
        }
        TreeNode root = buildTree(preorder, 0, inorder.length - 1, inorderSeqIndex);
        return root;
    }

    public TreeNode buildTree(int[] preorder, int left, int right, Map<Integer, Integer> indexMap) {
        if (left > right) {
            return null;
        }
        int val = preorder[preIndex];
        preIndex++;
        TreeNode root = new TreeNode(val);
        int inorderIndex = indexMap.get(val);
        root.left = buildTree(preorder, left, inorderIndex - 1, indexMap);
        root.right = buildTree(preorder, inorderIndex + 1, right, indexMap);
        return root;
    }
}
