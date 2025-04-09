package com.leetcode.medium;

import com.leetcode.ListNode;

public class _86PartitionList {
    public ListNode partition(ListNode head, int x) {

        if (head == null || head.next == null) {
            return head;
        }

        ListNode lessXHead = null, lessXTail = null;
        ListNode greaterXHead = null, greaterXTail = null;
        ListNode cur = head;
        while (cur != null) {
            if (cur.val < x) {
                if (lessXHead == null) {
                    lessXHead = lessXTail = cur;
                } else {
                    lessXTail.next = cur;
                    lessXTail = cur;
                }
            } else {
                if (greaterXHead == null) {
                    greaterXHead = greaterXTail = cur;
                } else {
                    greaterXTail.next = cur;
                    greaterXTail = cur;
                }
            }
            cur = cur.next;
        }

        if (greaterXTail == null) {
            return lessXHead;
        }
        if (lessXTail == null) {
            return greaterXHead;
        }

        greaterXTail.next = null;
        lessXTail.next = greaterXHead;

        return lessXHead;

    }
}
