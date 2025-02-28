package com.leetcode.medium;

import com.leetcode.ListNode;

public class _92ReverseLinkedList {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        int count = 1;
        ListNode leftNode = null;
        ListNode rightNode = null;
        ListNode tailNode = null;

        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            if (count + 1 == left) {
                leftNode = cur;
                leftNode.next = null;
            }
            if (count == left) {
                rightNode = cur;
            }
            if (count >= left) {
                cur.next = pre;
                pre = cur;
            }
            if (count == right) {
                tailNode = next;
                break;
            }
            cur = next;
            count++;
        }

        if (leftNode != null) {
            leftNode.next = pre;
        } else {
            head = pre;
        }
        if (rightNode != null) {
            rightNode.next = tailNode;
        }

        return head;
    }
}
