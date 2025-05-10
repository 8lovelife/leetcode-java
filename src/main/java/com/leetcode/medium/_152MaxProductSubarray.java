package com.leetcode.medium;

public class _152MaxProductSubarray {

    public int maxProduct3(int[] nums) {
        int n = nums.length;
        int curProduct = 1;
        int maxProduct = nums[0];
        for (int i = 0; i < n; i++) {
            if (curProduct == 0) {
                curProduct = 1;
            }
            curProduct *= nums[i];
            maxProduct = Math.max(maxProduct, curProduct);
        }

        curProduct = 1;
        for (int j = n - 1; j >= 0; j--) {
            if (curProduct == 0) {
                curProduct = 1;
            }
            curProduct *= nums[j];
            maxProduct = Math.max(maxProduct, curProduct);
        }

        return maxProduct;
    }

    public int maxProduct2(int[] nums) {
        int n = nums.length;
        int maxProduct = nums[0], l = 0, r = 0;
        for (int i = 0; i < n; i++) {
            l = l == 0 ? 1 : l;
            r = r == 0 ? 1 : r;
            l *= nums[i];
            r *= nums[n - 1 - i];
            maxProduct = Math.max(maxProduct, Math.max(l, r));
        }
        return maxProduct;
    }

    public int maxProduct(int[] nums) {
        int n = nums.length;
        int maxProduct = nums[0], curMaxProduct = nums[0], curMinProduct = nums[0];
        for (int i = 1; i < n; i++) {
            int num = nums[i];
            if (num < 0) {
                int tmp = curMaxProduct;
                curMaxProduct = curMinProduct;
                curMinProduct = tmp;
            }
            curMaxProduct = Math.max(curMaxProduct * num, num);
            curMinProduct = Math.min(curMinProduct * num, num);
            maxProduct = Math.max(maxProduct, curMaxProduct);
        }
        return maxProduct;
    }

}
