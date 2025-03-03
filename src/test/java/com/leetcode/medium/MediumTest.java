package com.leetcode.medium;

import java.util.*;

import org.junit.Test;

import com.leetcode.ListNode;
import com.leetcode.TreeNode;

public class MediumTest {

    @Test
    public void _105() {
        // Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
        // Output: [3,9,20,null,null,15,7]
        int[] preorder = new int[] { 3, 9, 20, 15, 7 };
        int[] inorder = new int[] { 9, 3, 15, 20, 7 };
        TreeNode root = new _105ConstructBinaryTree().buildTree(preorder, inorder);
        root.show();
    }

    @Test
    public void _102() {
        TreeNode rooNode = new TreeNode(1);
        new _102BinaryTreeLevelOrderTraversal().levelOrderRecursive(rooNode);
    }

    @Test
    public void _92() {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);
        head.show();
        ListNode newHead = new _92ReverseLinkedList().reverseBetween(head, 1, 2);
        newHead.show();
    }

    @Test
    public void _684() {
        int[][] edges = new int[][] { { 1, 2 }, { 1, 3 }, { 2, 3 } };
        int[] result = new _684RedundantConnection().findRedundantConnectionPostBFS(edges);
        System.out.println("result " + Arrays.toString(result));
    }

}