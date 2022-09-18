package com.leetcode.easy;

import org.junit.Assert;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class EasyTest {

    @Test
    public void twoSum() {
        int[] nums = new int[] { 1, 3, 7, 5, 4 };
        int target = 4;
        int[] result = TwoSum.twoSum(nums, target);
        Assert.assertTrue(result != null
                && (nums[result[0]] + nums[result[1]]) == target);
    }

    @Test
    public void addTwoNumbers() {
        int[] l1 = new int[] { };
        int[] l2 = new int[] { 0,1};
        ListNode l1DummyHead = new ListNode();
        ListNode currL1 = l1DummyHead;
        for (int i = 0; i < l1.length; i++) {
            currL1.next = new ListNode(l1[i]);
            currL1 = currL1.next;
        }
        ListNode l2DummyHead = new ListNode();
        ListNode curryL2 = l2DummyHead;
        for (int j = 0; j < l2.length; j++) {
            curryL2.next = new ListNode(l2[j]);
            curryL2 = curryL2.next;
        }
        System.out.println(l1DummyHead.next);
        System.out.println(l2DummyHead.next);
        ListNode l3 = AddTwoNumbers.addTwoNumbers(l1DummyHead.next,l2DummyHead.next);
        System.out.println(l3);
    }

    @Test
    public void testLongestLenthSubString(){
        int lengthOfLongestSubstring = LengthOfLongestSubstring.lengthOfLongestSubstring("abaab!bb");
        System.out.println(lengthOfLongestSubstring);
    }
}
