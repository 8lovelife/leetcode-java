package com.leetcode.easy;

import java.util.*;
import java.util.stream.Collectors;

import com.leetcode.TreeNode;

public class _257BinaryTreePath {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> paths = new ArrayList<>();
        path(root, paths, new ArrayList<>());
        return paths;
    }

    private void path(TreeNode root, List<String> paths, List<Integer> path) {
        if (root == null)
            return;

        path.add(root.val);
        if (root.left == null && root.right == null) {
            paths.add(path.stream().map(String::valueOf).collect(Collectors.joining("->")));
        } else {
            path(root.left, paths, path);
            path(root.right, paths, path);
        }
        path.remove(path.size() - 1);
    }

    public List<String> binaryTreePathsSb(TreeNode root) {
        List<String> paths = new ArrayList<>();
        pathSb(root, paths, new StringBuilder());
        return paths;
    }

    private void pathSb(TreeNode root, List<String> paths, StringBuilder sb) {
        if (root == null)
            return;

        Integer length = sb.length();
        sb.append(root.val);
        if (root.left == null && root.right == null) {
            paths.add(sb.toString());
        } else {
            sb.append("->");
            pathSb(root.left, paths, sb);
            pathSb(root.right, paths, sb);
        }
        sb.setLength(length);
    }
}
