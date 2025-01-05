package com.leetcode.medium;

import java.util.PriorityQueue;
import java.util.Queue;

public class _785Bipartite {


    public Boolean isBipartiteBFS(int[][] graph){
        int n = graph.length;
        int[] vState = new int[n];
        for (int i = 0; i < n; i++) {
            if (vState[i]!=0) {
                continue;
            }
            Queue<Integer> pq = new PriorityQueue<>();
            pq.add(i);
            vState[i] = 1;
            while (!pq.isEmpty()) {
                int node = pq.poll();
                for (int neighbor : graph[node]) {
                    if (vState[neighbor] == 0) {
                        vState[neighbor] = -vState[node];
                        pq.add(neighbor);
                    } else if (vState[neighbor] == vState[node] ) {
                        return false;
                    }
                }
            }
        }
        return true;
    }


    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        int[] vState = new int[n];
        for (int i = 0; i < n; i++) {
            if (vState[i] != 0) {
                continue;
            }
            if (oddCycleExist(i, 1, vState, graph)) {
                return false;
            }

        }
        return true;
    }

    private Boolean oddCycleExist(int node, int currentNodeState, int[] vState, int[][] graph) {
        vState[node] = currentNodeState;
        for (int neighbor : graph[node]) {
            if (vState[neighbor] == 0) {
                if (oddCycleExist(neighbor, -currentNodeState, vState, graph)) {
                    return true;
                }
            } else if (vState[neighbor] == vState[node]) {
                return true;
            }
        }

        return false;
    }

}
