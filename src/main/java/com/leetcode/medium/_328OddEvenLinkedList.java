package com.leetcode.medium;

import com.leetcode.ListNode;

public class _328OddEvenLinkedList {

    public ListNode oddEvenList(ListNode head) {

        if (head == null || head.next == null) {
            return head;
        }

        ListNode oddCur = head;
        ListNode evenHead = head.next;
        ListNode evenCur = evenHead;

        while (oddCur != null || evenCur != null) {
            if (oddCur.next == null) {
                break;
            }

            if (evenCur.next == null) {
                break;
            }
            oddCur.next = oddCur.next.next; // odd
            oddCur = oddCur.next;

            evenCur.next = evenCur.next.next; // even
            evenCur = evenCur.next;

        }

        oddCur.next = evenHead;

        return head;
    }

    public ListNode oddEvenListSimplify(ListNode head) {

        if (head == null || head.next == null) {
            return head;
        }

        ListNode oddCur = head;
        ListNode evenHead = head.next;
        ListNode evenCur = evenHead;

        while (evenCur != null && evenCur.next != null) {

            oddCur.next = evenCur.next;
            oddCur = oddCur.next;

            evenCur.next = oddCur.next;
            evenCur = evenCur.next;

        }

        oddCur.next = evenHead;

        return head;
    }
}
