package com.leetcode.medium;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class _787CheapestFlightsWithKStep {

    public static int INF = 0x3f3f3f3f;

    public static void main(String[] args) {
        int price = findCheapestPrice(2,new int[][]{{0,1,2}},1,0,0);
        System.out.println(price);
    }

    public static int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {

        int[][] adjMatrix = new int[n][n];
        for (int[] i : adjMatrix) {
            Arrays.fill(i, INF);
        }
        for (int[] flight : flights) {
            int u = flight[0];
            int v = flight[1];
            int cost = flight[2];
            adjMatrix[u][v] = cost;
        }

        int[] distance = new int[n];
        Arrays.fill(distance, INF);
        distance[src] = 0;
        // Queue<int[]> pq = new ArrayDeque<int[]>(n);
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>(n, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o2[2] - o1[2];
            }
        });
        pq.add(new int[] { src, 0, k + 1 });
        while (!pq.isEmpty()) {
            int[] miniV = pq.poll();
            int v = miniV[0];
            int price = miniV[1];
            int remain = miniV[2];
            if (remain > 0) {
                for (int j = 0; j < n; j++) {
                    int cost = price + adjMatrix[v][j];
                    if (cost < distance[j]) {
                        distance[j] = cost;
                        pq.add(new int[] { j, cost, remain - 1 });
                    }
                }
            }
        }
        return distance[dst] == INF ? -1 : distance[dst];
    }

}
