package com.leetcode.medium;

public class _33SearchInRotatedSortedArray {

    public int searchSpecificBoundary(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int midNum = nums[mid];

            if (midNum == target) {
                return mid;
            } else if (midNum < nums[right]) {
                if (target <= nums[right] && target > midNum) {
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

        return -1;
    }

    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int midNum = nums[mid];

            if (midNum == target) {
                return mid;
            }

            if (midNum <= nums[right]) {

                if (target <= nums[right] && target >= midNum) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }

            } else {
                if (target >= nums[left] && target <= midNum) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }

            }

        }

        return -1;
    }
}
