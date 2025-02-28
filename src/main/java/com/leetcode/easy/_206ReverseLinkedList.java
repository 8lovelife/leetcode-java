package com.leetcode.easy;

import java.util.Stack;

import com.leetcode.ListNode;

class BuildALinkedList {
    public ListNode head, tail;

    public void headInsertion(int val) {
        ListNode node = new ListNode(val);
        node.next = head;
        head = node;
    }

    public void tailInsertion(int val) {
        ListNode node = new ListNode(val);
        if (head == null) {
            head = tail = node;
        } else {
            tail.next = node;
            tail = node;
        }
    }

    public static void main(String[] args) {
        BuildALinkedList bd = new BuildALinkedList();
        bd.tailInsertion(1);
        bd.tailInsertion(2);
        bd.tailInsertion(3);
        bd.head.show();
    }
}

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
        if (head == null || head.next == null) {
            return head;
        }
        ListNode reversedNode = reverse(head.next);
        head.next.next = head;
        head.next = null;
        return reversedNode;
    }
}
