package com.leetcode.medium;

public class _238ProductArrayExceptSelf {

    public int[] productExceptSelfMoreClear(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        int prefixProduct = 1;
        for (int i = 0; i < n; i++) {
            ans[i] = prefixProduct;
            prefixProduct *= nums[i];
        }
        int suffixProduct = 1;
        for (int i = n - 1; i >= 0; i--) {
            ans[i] *= suffixProduct;
            suffixProduct *= nums[i];
        }
        return ans;
    }

    // O(N) with 1 space
    public int[] productExceptSelf3(int[] nums) {
        int n = nums.length;
        int[] prefixProduct = new int[n];
        prefixProduct[0] = 1;
        for (int i = 1; i < n; i++) {
            prefixProduct[i] = prefixProduct[i - 1] * nums[i - 1];
        }

        int suffixProduct = 1;
        for (int j = n - 2; j >= 0; j--) {
            suffixProduct *= nums[j + 1];
            prefixProduct[j] = prefixProduct[j] * suffixProduct;
        }

        return prefixProduct;
    }

    // O(N) with N space
    public int[] productExceptSelf2(int[] nums) {
        int n = nums.length;
        int[] prefixProduct = new int[n];
        prefixProduct[0] = 1;
        for (int i = 1; i < n; i++) {
            prefixProduct[i] = prefixProduct[i - 1] * nums[i - 1];
        }

        int[] suffixProduct = new int[n];
        suffixProduct[n - 1] = 1;
        for (int j = n - 2; j >= 0; j--) {
            suffixProduct[j] = suffixProduct[j + 1] * nums[j + 1];
        }

        int[] ans = new int[n];
        for (int k = 0; k < n; k++) {
            ans[k] = prefixProduct[k] * suffixProduct[k];
        }
        return ans;
    }

    // TLE n to the power of 2
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            int product = 1;
            for (int j = 0; j < n; j++) {
                if (j != i) {
                    product *= nums[j];
                }
            }
            ans[i] = product;
        }
        return ans;
    }
}
