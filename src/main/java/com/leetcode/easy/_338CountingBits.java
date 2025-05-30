package com.leetcode.easy;

public class _338CountingBits {
    public int[] countBits(int n) {
        int[] ans = new int[n + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = hammingWeight(i);
        }
        return ans;
    }

    private int hammingWeight(int i) {
        int hammingWeight = 0;
        while (i > 0) {
            int bit = i & 1; // get the last bit
            if (bit == 1) {
                hammingWeight++;
            }
            i >>= 1;
        }
        return hammingWeight;
    }

    private int hammingWeight2(int i) {
        int hammingWeight = 0;
        while (i > 0) {
            hammingWeight++;
            i &= i - 1; // remove the last 1
        }
        return hammingWeight;
    }
}
