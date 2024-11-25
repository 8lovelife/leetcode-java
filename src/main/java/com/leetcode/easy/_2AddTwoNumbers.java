package com.leetcode.easy;

import lombok.Data;

public class _2AddTwoNumbers {
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode curr = dummyHead;
        int carry = 0;
        while (l1 != null || l2 != null || carry != 0) {
            int l1Var = l1 != null ? l1.val : 0;
            int l2Var = l2 != null ? l2.val: 0;
            int sum = l1Var + l2Var + carry;
            carry = sum / 10; // 取整
            int remainder = sum % 10; // 取余
            curr.next = new ListNode(remainder);
            curr = curr.next;
            if (l1!= null) {
                l1 = l1.next;
            }
            if (l2!= null) {
                l2 = l2.next;
            }
        }
        return dummyHead.next;
    }
}

@Data
class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
