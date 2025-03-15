package com.leetcode.medium;

public class _695MaxAreaIsland {

    public int maxAreaOfIsland(int[][] grid) {
        int rL = grid.length;
        int cL = grid[0].length;
        int maxArea = 0;
        for (int r = 0; r < rL; r++) {
            for (int c = 0; c < cL; c++) {
                if (grid[r][c] == 1) {
                    int[] area = { 0 };
                    dfs(grid, r, rL, c, cL, area);
                    maxArea = Math.max(area[0], maxArea);
                }
            }
        }
        return maxArea;
    }

    private void dfs(int[][] grid, int r, int rL, int c, int cL, int[] area) {
        if (r >= rL || r < 0 || c >= cL || c < 0 || grid[r][c] == 0) {
            return;
        }
        grid[r][c] = 0;
        area[0]++;
        dfs(grid, r - 1, rL, c, cL, area);
        dfs(grid, r + 1, rL, c, cL, area);
        dfs(grid, r, rL, c - 1, cL, area);
        dfs(grid, r, rL, c + 1, cL, area);
    }

    public int maxAreaOfIslandWithReturn(int[][] grid) {
        int rL = grid.length;
        int cL = grid[0].length;
        int maxArea = 0;
        for (int r = 0; r < rL; r++) {
            for (int c = 0; c < cL; c++) {
                if (grid[r][c] == 1) {
                    maxArea = Math.max(dfsWithReturn(grid, r, rL, c, cL), maxArea);
                }
            }
        }
        return maxArea;
    }

    private int dfsWithReturn(int[][] grid, int r, int rL, int c, int cL) {
        if (r >= rL || r < 0 || c >= cL || c < 0 || grid[r][c] == 0) {
            return 0;
        }
        grid[r][c] = 0;
        int count = 1;
        count += dfsWithReturn(grid, r - 1, rL, c, cL);
        count += dfsWithReturn(grid, r + 1, rL, c, cL);
        count += dfsWithReturn(grid, r, rL, c - 1, cL);
        count += dfsWithReturn(grid, r, rL, c + 1, cL);
        return count;
    }
}
