package com.leetcode.medium;

import java.util.ArrayDeque;
import java.util.Queue;

public class _200NumberOfIsland {

    public int numIslands(char[][] grid) {
        int rL = grid.length;
        int cL = grid[0].length;
        int num = 0;
        for (int r = 0; r < rL; r++) {
            for (int c = 0; c < cL; c++) {
                if (grid[r][c] == '1') {
                    num++;
                    dfs(grid, r, rL, c, cL);
                }
            }
        }
        return num;
    }

    private void dfs(char[][] grid, int r, int rL, int c, int cL) {
        if (r >= rL || r < 0 || c < 0 || c >= cL || grid[r][c] == '0') {
            return;
        }
        grid[r][c] = '0';
        dfs(grid, r - 1, rL, c, cL);
        dfs(grid, r + 1, rL, c, cL);
        dfs(grid, r, rL, c - 1, cL);
        dfs(grid, r, rL, c + 1, cL);
    }

    public int numIslandsIterate(char[][] grid) {
        int rL = grid.length;
        int cL = grid[0].length;
        int num = 0;
        int[][] topDownLeftRight = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
        Queue<int[]> q = new ArrayDeque<>();
        for (int r = 0; r < rL; r++) {
            for (int c = 0; c < cL; c++) {
                if (grid[r][c] == '1') {
                    num++;
                    q.offer(new int[] { r, c });
                    while (!q.isEmpty()) {
                        int[] cur = q.poll();
                        int row = cur[0];
                        int column = cur[1];

                        if (row < 0 || row >= rL || column < 0 || column >= cL || grid[row][column] == '0') {
                            continue;
                        }

                        grid[row][column] = '0';
                        for (int[] tdlr : topDownLeftRight) {
                            int nextR = row + tdlr[0];
                            int nextC = column + tdlr[1];
                            q.offer(new int[] { nextR, nextC });
                        }

                    }
                }
            }
        }
        return num;
    }
}
