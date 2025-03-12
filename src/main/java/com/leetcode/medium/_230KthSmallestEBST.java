package com.leetcode.medium;

import com.leetcode.TreeNode;

import java.util.*;

public class _230KthSmallestEBST {

    public int kthSmallest(TreeNode root, int k) {
        List<Integer> result = new ArrayList<>();
        kthSmallest(root, result);
        return result.get(k - 1);
    }

    public void kthSmallest(TreeNode node, List<Integer> result) {
        if (node == null) {
            return;
        }

        kthSmallest(node.left, result);
        result.add(node.val);
        kthSmallest(node.right, result);
    }


    public int kthSmallestWithReturn(TreeNode root, int k) {
        int[] kth = {1};
        return kthSmallestWithReturn(root, kth, k);
    }

    public int kthSmallestWithReturn(TreeNode node, int[] kth, int k) {
        if (node == null) {
            return -1;
        }
        int left = kthSmallestWithReturn(node.left, kth, k);
        if (left != -1) {
            return left;
        }
        if (kth[0] == k) {
            return node.val;
        }
        kth[0]++;
        return kthSmallestWithReturn(node.right, kth, k);

    }

    public int kthSmallestIterate(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode tmp = root;
        while (!stack.isEmpty() || tmp != null) {
            while (tmp != null) {
                stack.push(tmp);
                tmp = tmp.left;
            }
            TreeNode node = stack.pop();
            if (k-- == 1) {
                return node.val;
            }
            tmp = node.right;
        }

        return -1;
    }
}