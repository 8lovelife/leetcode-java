package com.leetcode.easy;

public class _191NumberOf1Bits {
    public int hammingWeight(int n) {
        int hammingWeight = 0;
        while (n > 0) {
            int bit = n & 1; // check the last bit
            if (bit == 1) {
                hammingWeight++;
            }
            n >>= 1;
        }
        return hammingWeight;
    }

    public int hammingWeight2(int n) {
        int hammingWeight = 0;
        while (n > 0) {
            hammingWeight++;
            n &= n - 1; // remove the last bit which is '1'
        }
        return hammingWeight;
    }
}
