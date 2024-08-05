package com.leetcode.hard;

import java.util.*;

public class _778SwimInRisingWater {
    public static void main(String[] args) {

        int leastTime = swimInWater(new int[][]{{7,8,10,13},{2,4,15,11},{12,1,5,6},{9,3,0,14}});
        System.out.println(leastTime);
        
    }

    // TODO
    public static int swimInWaterBSTandBFS(int[][] grid) {
        return 0;
    }
    public static int swimInWater(int[][] grid) {
        int n = grid.length;
        int INF = 0x3f3f3f3f;
        int[][] leastTimes = new int[n][n];
        for (int[] time : leastTimes) {
            Arrays.fill(time, INF);
        }

        int[][] directions = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

        Queue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[2] - o2[2];
            }
        });
        pq.add(new int[] { 0, 0, grid[0][0] });
        while (!pq.isEmpty()) {
            int[] node = pq.poll();
            int x = node[0];
            int y = node[1];
            int time = node[2];
            if (x == n - 1 && y == n - 1) {
                return time;
            }
            for (int[] direction : directions) {
                int nextX = x + direction[0];
                int nextY = y + direction[1];
                if (nextX < 0 || nextX >= n || nextY < 0 || nextY >= n) {
                    continue;
                }
                int currNeedTime = Math.max(time, grid[nextX][nextY]);
                if (currNeedTime < leastTimes[nextX][nextY]) {
                    leastTimes[nextX][nextY] = currNeedTime;
                    pq.add(new int[] { nextX, nextY, currNeedTime });
                }
            }
        }
        return leastTimes[n - 1][n - 1];
    }
}
