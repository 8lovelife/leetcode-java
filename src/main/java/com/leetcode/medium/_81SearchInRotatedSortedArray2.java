package com.leetcode.medium;

public class _81SearchInRotatedSortedArray2 {
    public boolean search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int midNum = nums[mid];
            if (midNum == target) {
                return true;
            }

            if (midNum == nums[left] && nums[right] == midNum) {
                left++;
                right--;
            } else if (midNum <= nums[right]) {
                if (target > midNum && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            } else {
                if (target >= nums[left] && target < midNum) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
        }

        return false;
    }
}
