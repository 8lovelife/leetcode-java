package com.leetcode.medium;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class _1091ShortestPathInBinaryMatrix {

    public static void main(String[] args) {
        int shortestPath = shortestPathBinaryMatrixBFS(new int[][]{{0,1},{1,0}});
        System.out.println(shortestPath);
    }

    public static int shortestPathBinaryMatrixBFS(int[][] grid){
        int n = grid.length;
        if (grid[0][0] == 1 || grid[n - 1][n - 1] == 1) {
            return -1;
        }
        Queue<int[]> pq = new ArrayDeque<int[]>(n * n);
        pq.add(new int[] { 0, 0 });

        int[][] adj8Direction = new int[][] { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 }, { -1, -1 }, { -1, 1 },
                { 1, -1 }, { 1, 1 } };
        int ans = 1;
        while (!pq.isEmpty()) {
            int size = pq.size();
            for (int i = 0; i < size; i++) {
                int[] node = pq.poll();
                int x = node[0];
                int y = node[1];
                if (x == n - 1 && y == n - 1) {
                    return ans;
                }
                for (int[] direction : adj8Direction) {
                    int nextX = direction[0] + x;
                    int nextY = direction[1] + y;
                    if (nextX < 0 || nextX == n || nextY < 0 || nextY == n || grid[nextX][nextY] == 1) {
                        continue;
                    }
                    pq.add(new int[] { nextX, nextY });
                    grid[nextX][nextY] = 1;
                }
            }

            ans++;

        }
        return -1;
    }
    public static int shortestPathBinaryMatrix(int[][] grid) {
        
        if(grid[0][0] == 1){
            return -1;
        }
        int INF = 0x3f3f3f3f;
        int n = grid.length;
        int[][] distance = new int[n][n];
        for (int[] dis : distance) {
            Arrays.fill(dis, INF);
        }
        distance[0][0] = 1;
        // PriorityQueue<int[]> pq = new PriorityQueue<int[]>(n, new Comparator<int[]>() {
        //     @Override
        //     public int compare(int[] o1, int[] o2) {
        //         return o1[2] - o2[2];
        //     }
        // });
        Queue<int[]> pq = new ArrayDeque<int[]>(n);
        pq.add(new int[] { 0, 0, 1 });
    
        int[][] adj8Direction = new int[][] { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 }, { -1, -1 }, { -1, 1 },
                { 1, -1 }, { 1, 1 } };
        while (!pq.isEmpty()) {
            int[] node = pq.poll();
            int x = node[0];
            int y = node[1];
            int step = node[2];

            for (int[] direction : adj8Direction) {
                int nextX = direction[0] + x;
                int nextY = direction[1] + y;
                if (nextX < 0 || nextX == n || nextY < 0 || nextY == n || grid[nextX][nextY] == 1) {
                    continue;
                }
                int steps = step + 1;
                if (distance[nextX][nextY] > steps) {
                    distance[nextX][nextY] = steps;
                    pq.add(new int[] { nextX, nextY, steps });
                }
            }

        }

        return distance[n-1][n - 1] == INF ? -1 : distance[n-1][n - 1];
        
    }
}
