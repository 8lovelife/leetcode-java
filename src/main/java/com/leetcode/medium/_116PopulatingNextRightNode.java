package com.leetcode.medium;

import java.util.*;
import com.leetcode.Node;

public class _116PopulatingNextRightNode {

    public Node connect(Node root) {
        if (root == null) {
            return root;
        }
        Queue<Node> q = new ArrayDeque<>();
        q.offer(root);
        while (!q.isEmpty()) {
            int size = q.size();
            Node pre = null;
            while (size-- > 0) {
                Node cur = q.poll();
                if (cur.left != null)
                    q.offer(cur.left);
                if (cur.right != null)
                    q.offer(cur.right);
                if (pre != null) {
                    pre.next = cur;
                }
                pre = cur;
            }
        }
        return root;
    }

    public Node connectReverseQ(Node root) {
        if (root == null) {
            return root;
        }
        Queue<Node> q = new ArrayDeque<>();
        q.offer(root);
        while (!q.isEmpty()) {
            int size = q.size();
            Node pre = null;
            while (size-- > 0) {
                Node cur = q.poll();
                cur.next = pre;
                pre = cur;
                if (cur.left != null) {
                    q.offer(cur.right);
                    q.offer(cur.left);
                }
            }
        }
        return root;
    }

    public Node connectNode(Node root) {
        if (root == null || root.left == null || root.right == null) {
            return root;
        }
        root.left.next = root.right;
        if (root.next != null) {
            root.right.next = root.next.left;
        }
        connectNode(root.left);
        connectNode(root.right);
        return root;
    }

    public Node connectIteration(Node root) {
        if (root == null) {
            return root;
        }
        Node leftNode = root;
        while (leftNode.left != null) {
            Node cur = leftNode;
            while (cur != null) {
                if (cur.left != null) {
                    cur.left.next = cur.right;
                }
                if (cur.next != null) {
                    cur.right.next = cur.next.left;
                }
                cur = cur.next;
            }
            leftNode = leftNode.left;
        }
p
        return root;
    }
}
