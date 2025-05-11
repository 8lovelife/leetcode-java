package com.leetcode.medium;

import java.util.Arrays;

public class _2906ProductMatrix {
    public int[][] constructProductMatrix(int[][] grid) {
        int rL = grid.length;
        int cL = grid[0].length;
        int[][] ans = new int[rL][cL];
        int prefixProduct = 1;
        for (int i = 0; i < rL; i++) {
            for (int j = 0; j < cL; j++) {
                ans[i][j] = prefixProduct;
                prefixProduct *= grid[i][j];
            }
        }

        for (int[] result : ans) {
            System.out.println(Arrays.toString(result));
        }

        return ans;
    }
}
