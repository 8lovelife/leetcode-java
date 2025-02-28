package com.leetcode.medium;

import java.util.Arrays;

import org.junit.Test;

import com.leetcode.ListNode;

public class MediumTest {

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