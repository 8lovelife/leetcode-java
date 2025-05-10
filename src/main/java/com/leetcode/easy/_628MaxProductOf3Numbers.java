package com.leetcode.easy;

import java.util.Arrays;

public class _628MaxProductOf3Numbers {
    public int maximumProduct(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        return Math.max(nums[n - 1] * nums[n - 2] * nums[n - 3], nums[n - 1] * nums[1] * nums[0]);
    }

    public int maximumProduct2(int[] nums) {
        int n = nums.length;
        cocktail(nums);
        return Math.max(nums[n - 1] * nums[n - 2] * nums[n - 3], nums[n - 1] * nums[1] * nums[0]);
    }

    private void cocktail(int[] nums) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < nums.length - 1 - i; j++) {
                if (nums[j] > nums[j + 1]) {
                    int tmp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = tmp;
                }
            }

            for (int j = nums.length - 2 - i; j > i; j--) {
                if (nums[j] < nums[j - 1]) {
                    int tmp = nums[j];
                    nums[j] = nums[j - 1];
                    nums[j - 1] = tmp;
                }
            }
        }
    }
}
