package com.leetcode.medium;

import com.leetcode.Node;

public class _117PolulatingNextRightNode {

    public Node connectIteration(Node root) {
        if (root == null)
            return root;

        Node startLevelNode = root;
        while (startLevelNode != null) {
            Node cur = startLevelNode;
            while (cur != null) {
                if (cur.left != null) {
                    cur.left.next = cur.right;
                }
                if (cur.right != null) {
                    cur.right.next = findNext(cur.next);
                } else if (cur.left != null) {
                    cur.left.next = findNext(cur.next);
                }
                cur = cur.next;
            }
            startLevelNode = findNext(startLevelNode);
        }
        return root;
    }

    public Node connect(Node root) {
        if (root == null)
            return root;
        if (root.left != null) {
            root.left.next = root.right;
        }
        if (root.right != null) {
            root.right.next = findNext(root.next);
        } else if (root.left != null) {
            root.left.next = findNext(root.next);
        }
        connect(root.right);
        connect(root.left);

        return root;
    }

    private Node findNext(Node root) {
        Node cur = root;
        while (cur != null) {
            if (cur.left != null)
                return cur.left;
            if (cur.right != null)
                return cur.right;
            cur = cur.next;
        }
        return null;
    }
}
