package com.leetcode.medium;

import java.util.*;

import com.leetcode.TreeNode;

public class _236LowestCommonAncenstor {

    public TreeNode lowestCommonAncestorRecursive(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if (left != null && right != null) {
            return root;
        }

        if (left == null) {
            return right;
        }

        return left;
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        Map<TreeNode, TreeNode> nodeAncestorMap = new HashMap<>();
        recordAncestor(root, null, nodeAncestorMap);

        List<TreeNode> pAncestor = new ArrayList<>();
        TreeNode pCur = p;
        while (pCur != null) {
            pAncestor.add(pCur);
            pCur = nodeAncestorMap.get(pCur);
        }

        TreeNode qCur = q;
        while (qCur != null) {
            if (pAncestor.contains(qCur)) {
                return qCur;
            }
            qCur = nodeAncestorMap.get(qCur);
        }
        return null;
    }

    private void recordAncestor(TreeNode root, TreeNode parent, Map<TreeNode, TreeNode> nodeAncestorMap) {
        if (root == null) {
            return;
        }
        if (parent != null) {
            nodeAncestorMap.put(root, parent);
        }
        recordAncestor(root.left, root, nodeAncestorMap);
        recordAncestor(root.right, root, nodeAncestorMap);
    }
}
