package com.leetcode.medium;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class _1631PathWithMiniEffort {
    public static void main(String[] args) {
        int efforts = minimumEffortPath(new int[][]{{1,2,2},{3,8,2},{5,3,5}});
        System.out.println(efforts);
    }

    // TODO
    public static int minimumEffortPathBSTandBFS(int[][] heights) {
        return -1;

    }


    public static int minimumEffortPath(int[][] heights) {
        int INF = 0x3f3f3f3f;
        int rows = heights.length;
        int columns = heights[0].length;
        // max different of consecutive cells
        int[][] efforts = new int[rows][columns];
        for (int[] effort : efforts) {
            Arrays.fill(effort, INF);
        }
        int[][] adjDirection = new int[][] { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
        Queue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[2] - o2[2];
            }
        });
        pq.add(new int[] { 0, 0, 0 });
        while (!pq.isEmpty()) {
            int[] node = pq.poll();
            int row = node[0];
            int column = node[1];
            int effort = node[2];
            // a lot optimization
            if (row == rows - 1 && column == columns - 1) {
                return effort;
            }

            for (int[] direction : adjDirection) {
                int nextRow = direction[0] + row;
                int nextColumn = direction[1] + column;
                if (nextRow < 0 || nextRow >= rows || nextColumn < 0 || nextColumn >= columns) {
                    continue;
                }
                int currEffort = Math.max(effort, Math.abs(heights[nextRow][nextColumn] - heights[row][column]));
                if (currEffort < efforts[nextRow][nextColumn]) {
                    efforts[nextRow][nextColumn] = currEffort;
                    pq.add(new int[] { nextRow, nextColumn, currEffort });
                }
            }
        }
        return efforts[rows - 1][columns - 1];
    }
}
