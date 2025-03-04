package com.leetcode.medium;

import java.util.*;

import com.leetcode.TreeNode;

public class ConstructBinaryTree {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right = new TreeNode(3);
        root.right.right = new TreeNode(6);
        List<Integer> preorder = root.showPreorder();
        List<Integer> postorder = root.showPostorder();
        List<Integer> inorder = root.showInorder();
        System.out.println("--------------------");
        TreeNode result = new ConstructBinaryTree().buildTree(
                preorder.stream().mapToInt(Integer::intValue).toArray(),
                postorder.stream().mapToInt(Integer::intValue).toArray());
        result.showInorder();
    }

    public TreeNode buildTree(int[] preorder, int[] postorder) {
        Map<Integer, Integer> postorderIndexs = new HashMap<>();
        for (int i = 0; i < postorder.length; i++) {
            postorderIndexs.put(postorder[i], i);
        }
        int[] preIndex = new int[1];
        TreeNode root = buildTree(preIndex, preorder, 0, preorder.length - 1, postorderIndexs);
        return root;
    }

    // public List<TreeNode> buildAllTree(int[] preorder, int[] postorder) {
    // Map<Integer, Integer> postorderIndexs = new HashMap<>();
    // for (int i = 0; i < postorder.length; i++) {
    // postorderIndexs.put(postorder[i], i);
    // }
    // int[] preIndex = new int[1];
    // List<TreeNode> root = buildAllTrees(preIndex, preorder, 0, preorder.length -
    // 1, postorderIndexs);
    // return root;
    // }

    private TreeNode buildTree(int[] preIndex, int[] preorder, int left, int right,
            Map<Integer, Integer> postorderIndexs) {
        if (left > right) {
            return null;
        }
        int val = preorder[preIndex[0]];
        TreeNode root = new TreeNode(val);
        preIndex[0]++;
        // is current node a leaf ?
        if (left == right || preIndex[0] >= preorder.length) {
            return root;
        }
        int leftSubTreeRoot = preorder[preIndex[0]];
        int leftIndex = postorderIndexs.get(leftSubTreeRoot);
        root.left = buildTree(preIndex, preorder, left, leftIndex, postorderIndexs);
        root.right = buildTree(preIndex, preorder, leftIndex + 1, right - 1, postorderIndexs);
        return root;
    }

    // private List<TreeNode> buildAllTrees(int[] preIndex, int[] preorder, int
    // left, int right,
    // Map<Integer, Integer> postorderIndexs) {

    // List<TreeNode> trees = new ArrayList<>();
    // if (left > right || preIndex[0] >= preorder.length) {
    // return trees;
    // }

    // int currIndex = preIndex[0]; // Keep track of current index
    // TreeNode root = new TreeNode(preorder[preIndex[0]++]);

    // if (left == right) { // Leaf node case
    // trees.add(root);
    // return trees;
    // }

    // // Try different possible left-right subtree combinations
    // for (int split = left; split < right; split++) {
    // List<TreeNode> leftTrees = buildAllTrees(preIndex, preorder, left, split,
    // postorderIndexs);
    // List<TreeNode> rightTrees = buildAllTrees(preIndex, preorder, split + 1,
    // right, postorderIndexs);

    // for (TreeNode l : leftTrees) {
    // for (TreeNode r : rightTrees) {
    // TreeNode newRoot = new TreeNode(root.val); // Clone root for each possibility
    // newRoot.left = l;
    // newRoot.right = r;
    // trees.add(newRoot);
    // }
    // }
    // }

    // return trees;
    // }

}
