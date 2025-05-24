package com.leetcode.easy;

public class _704BinarySearch {
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int midNum = nums[mid];
            if (midNum == target) {
                return mid;
            } else if (target > midNum) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }
}
