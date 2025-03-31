package com.leetcode.medium;

public class _1905CountSubIsland {
    public int countSubIslands(int[][] grid1, int[][] grid2) {
        int rL = grid2.length;
        int cL = grid2[0].length;
        int subIslands = 0;
        for (int r = 0; r < rL; r++) {
            for (int c = 0; c < cL; c++) {
                if (grid2[r][c] == 1) {
                    subIslands += dfs(grid2, r, c, rL, cL, grid1);
                }
            }
        }

        return subIslands;
    }

    private int dfs(int[][] grid2, int r, int c, int rL, int cL, int[][] grid1) {

        int subIsland = grid1[r][c];
        grid2[r][c] = -1;
        if (r > 0 && grid2[r - 1][c] == 1) {
            subIsland &= dfs(grid2, r - 1, c, rL, cL, grid1);
        }
        if (r < rL - 1 && grid2[r + 1][c] == 1) {
            subIsland &= dfs(grid2, r + 1, c, rL, cL, grid1);

        }
        if (c > 0 && grid2[r][c - 1] == 1) {
            subIsland &= dfs(grid2, r, c - 1, rL, cL, grid1);
        }
        if (c < cL - 1 && grid2[r][c + 1] == 1) {
            subIsland &= dfs(grid2, r, c + 1, rL, cL, grid1);
        }
        return subIsland;
    }

    public int countSubIslands2(int[][] grid1, int[][] grid2) {
        int rL = grid2.length;
        int cL = grid2[0].length;
        int subIslands = 0;
        for (int r = 0; r < rL; r++) {
            for (int c = 0; c < cL; c++) {
                if (grid2[r][c] == 1 && dfs2(grid2, r, c, rL, cL, grid1)) {
                    subIslands++;
                }
            }
        }

        return subIslands;
    }

    private boolean dfs2(int[][] grid2, int r, int c, int rL, int cL, int[][] grid1) {

        boolean subIsland = true;

        if (r < rL && r >= 0 && c < cL && c >= 0 && grid2[r][c] == 1) {

            if (grid1[r][c] == 0) {
                return false;
            }
            grid2[r][c] = 0;
            boolean top = dfs2(grid2, r - 1, c, rL, cL, grid1);
            boolean down = dfs2(grid2, r + 1, c, rL, cL, grid1);
            boolean left = dfs2(grid2, r, c - 1, rL, cL, grid1);
            boolean right = dfs2(grid2, r, c + 1, rL, cL, grid1);
            subIsland = top && down && left && right;
        }

        return subIsland;
    }

}
