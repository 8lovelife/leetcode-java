package com.leetcode.easy;

import java.util.Stack;

import com.leetcode.ListNode;

public class _206ReverseLinkedList {

    public ListNode reverseList(ListNode head) {
        ListNode cur = head;
        ListNode pre = null;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    public ListNode reverseStack(ListNode head) {
        if (head == null) {
            return null;
        }
        Stack<ListNode> stack = new Stack<>();
        ListNode cur = head;
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }
        ListNode pre = null;
        ListNode reversedNode = null;
        while (!stack.isEmpty()) {
            ListNode node = stack.pop();
            if (pre == null) {
                reversedNode = node;
                pre = node;
            } else {
                pre.next = node;
                pre = node;
            }
        }
        pre.next = null;
        return reversedNode;
    }

    public ListNode reverse(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode reversedNode = reverse(head.next);
        head.next = reversedNode;
        head = head.next;
        return head;
    }
}
