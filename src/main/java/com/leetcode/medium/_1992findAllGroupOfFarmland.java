package com.leetcode.medium;

import java.util.*;

public class _1992findAllGroupOfFarmland {
    public int[][] findFarmland(int[][] land) {
        int rL = land.length;
        int cL = land[0].length;
        List<int[]> result = new ArrayList<>();
        for (int r = 0; r < rL; r++) {
            for (int c = 0; c < cL; c++) {
                if (land[r][c] == 1) {
                    int[] bottomRight = dfs(r, c, rL, cL, land);
                    result.add(new int[] { r, c, bottomRight[0], bottomRight[1] });
                }
            }
        }
        return result.toArray(new int[0][]);
    }

    private int[] dfs(int r, int c, int rL, int cL, int[][] land) {

        int[] bottomRight = { r, c };
        land[r][c] = 0;
        if (c < cL - 1 && land[r][c + 1] == 1) {
            int[] right = dfs(r, c + 1, rL, cL, land);
            bottomRight[1] = right[1];
        }
        if (r < rL - 1 && land[r + 1][c] == 1) {
            int[] bottom = dfs(r + 1, c, rL, cL, land);
            bottomRight[0] = bottom[0];
        }
        return bottomRight;

    }

    public int[][] findFarmlandWhile(int[][] land) {
        int rL = land.length;
        int cL = land[0].length;
        List<int[]> result = new ArrayList<>();
        for (int r = 0; r < rL; r++) {
            for (int c = 0; c < cL; c++) {
                if (land[r][c] == 1) {
                    int[] bottomRight = finish(r, c, rL, cL, land);
                    result.add(new int[] { r, c, bottomRight[0], bottomRight[1] });
                }
            }
        }
        return result.toArray(new int[0][]);
    }

    private int[] finish(int r, int c, int rL, int cL, int[][] land) {

        int c2 = c;
        while (c2 < cL - 1 && land[r][c2 + 1] == 1) {
            c2++;
        }

        int r2 = r;
        while (r2 < rL - 1 && land[r2 + 1][c] == 1) {
            r2++;
        }

        for (int i = r; i <= r2; i++) {
            for (int j = c; j <= c2; j++) {
                land[i][j] = 0;
            }
        }

        return new int[] { r2, c2 };

    }

}