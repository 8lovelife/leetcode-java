package com.leetcode;

import java.util.*;

public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode() {
    }

    public TreeNode(int val) {
        this.val = val;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    public List<Integer> showLevel() {
        List<Integer> ans = new ArrayList<>();
        Queue<TreeNode> q = new ArrayDeque<>();
        q.add(this);
        while (!q.isEmpty()) {
            TreeNode node = q.poll();
            ans.add(node.val);
            if (node.left != null) {
                q.add(node.left);
            }
            if (node.right != null) {
                q.add(node.right);
            }
        }
        System.out.println(Arrays.toString(ans.toArray()));
        return ans;
    }

    public List<Integer> showInorder() {
        List<Integer> result = new ArrayList<>();
        inorder(this, result);
        System.out.println(Arrays.toString(result.toArray()));
        return result;
    }

    public List<Integer> showPreorder() {
        List<Integer> result = new ArrayList<>();
        preorder(this, result);
        System.out.println(Arrays.toString(result.toArray()));
        return result;
    }

    public List<Integer> showPostorder() {
        List<Integer> result = new ArrayList<>();
        postorder(this, result);
        System.out.println(Arrays.toString(result.toArray()));
        return result;
    }

    private void inorder(TreeNode root, List<Integer> result) {
        if (root == null) {
            return;
        }
        inorder(root.left, result);
        result.add(root.val);
        inorder(root.right, result);
    }

    private void postorder(TreeNode root, List<Integer> result) {
        if (root == null) {
            return;
        }
        postorder(root.left, result);
        postorder(root.right, result);
        result.add(root.val);
    }

    private void preorder(TreeNode root, List<Integer> result) {
        if (root == null) {
            return;
        }
        result.add(root.val);
        preorder(root.left, result);
        preorder(root.right, result);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.show();
    }
}
