package com.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ListNode {
    public int val;
    public ListNode next;

    public ListNode() {
    }

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    public void show() {
        List<Integer> vals = new ArrayList<>();
        ListNode cur = this;
        while (cur != null) {
            vals.add(cur.val);
            cur = cur.next;
        }
        String result = vals.stream().map(String::valueOf)
                .collect(Collectors.joining("->"));
        System.out.println(result);
    }
}
