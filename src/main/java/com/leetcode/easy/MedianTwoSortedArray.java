package com.leetcode.easy;

public class MedianTwoSortedArray {
    public static double medianOfTwoSortedArray(int[] nums1, int[] nums2) {
        int nums1Length = nums1.length;
        int nums2Length = nums2.length;
        int[] nums = new int[nums1Length + nums2Length];
        int i = 0, j = 0, k = 0;
        while (i < nums1Length || j < nums2Length) { // 归并排序
            if (i == nums1Length) {
                for (int k2 = j; k2 < nums2.length; k2++) {
                    nums[k++] = nums2[k2];
                    j++;
                }
            } else if (j == nums2Length) {
                for (int k1 = i; k1 < nums1.length; k1++) {
                    nums[k++] = nums1[k1];
                    i++;
                }
            } else if (nums1[i] < nums2[j]) {
                nums[k++] = nums1[i++];
            } else {
                nums[k++] = nums2[j++];
            }
        }
        int size = nums.length / 2;
        if (nums.length % 2 == 0) {
            return (double) (nums[size] + nums[size - 1]) / 2;
        } else {
            return nums[size];
        }
    }
}
