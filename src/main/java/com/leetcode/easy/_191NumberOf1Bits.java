package com.leetcode.easy;

public class _191NumberOf1Bits {
    public int hammingWeight(int n) {
        int hammingWeight = 0;
        while (n > 0) {
            int bit = n & 1;
            if (bit == 1) {
                hammingWeight++;
            }
            n >>= 1;
        }
        return hammingWeight;
    }
}
