package com.leetcode.medium;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

class DisjointSet {
    int[] parents;

    DisjointSet(int n) {
        parents = new int[n];
        for (int i = 0; i < n; i++) {
            parents[i] = i;
        }
    }

    int findRoot(int u) {
        if (parents[u] == u) {
            return u;
        }
        parents[u] = findRoot(parents[u]);
        return parents[u];
    }

    int union(int u, int v) {
        int uRoot = findRoot(u);
        int vRoot = findRoot(v);
        if (uRoot != vRoot) {
            parents[uRoot] = vRoot;
        }
        return vRoot;
    }
}

public class _785Bipartite {

    public boolean isBipartiteDisjointSet(int[][] graph) {
        int n = graph.length;
        DisjointSet disjointSet = new DisjointSet(n);
        for (int i = 0; i < n; i++) {
            if (graph[i].length == 0) {
                continue;
            }
            int adjacentRoot = Arrays.stream(graph[i]).reduce((u, v) -> disjointSet.union(u, v)).getAsInt();
            if (disjointSet.findRoot(i) == adjacentRoot) {
                return false;
            }
        }
        return true;
    }

    public Boolean isBipartiteOddCycleDetectioinBFS(int[][] graph) {
        int n = graph.length;
        int[] vStatus = new int[n];
        for (int i = 0; i < n; i++) {
            if (vStatus[i] != 0) {
                continue;
            }
            Queue<Integer> q = new ArrayDeque<>(n);
            q.add(i);
            vStatus[i] = 1;
            while (!q.isEmpty()) {
                int v = q.poll();
                for (Integer neighbor : graph[v]) {
                    if (vStatus[neighbor] == 0) {
                        q.add(neighbor);
                        vStatus[neighbor] = -vStatus[v];
                    } else if (vStatus[neighbor] == vStatus[v]) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public Boolean isBipartiteOddCycleDetectionDFS(int[][] graph) {
        int n = graph.length;
        int[] vStatus = new int[n];
        for (int i = 0; i < n; i++) {
            if (vStatus[i] != 0) {
                continue;
            }
            if (oddCycle(i, 1, vStatus, graph)) {
                return false;
            }
        }
        return true;
    }

    private Boolean oddCycle(int node, int currentNodeColor, int[] vStatus, int[][] graph) {
        vStatus[node] = currentNodeColor;
        for (int neighbor : graph[node]) {
            if (vStatus[neighbor] == 0) {
                if (oddCycle(neighbor, -currentNodeColor, vStatus, graph)) {
                    return true;
                }
            } else if (vStatus[neighbor] == vStatus[node]) {
                return true;
            }
        }
        return false;
    }

    public Boolean isBipartiteBFS(int[][] graph) {
        int n = graph.length;
        int[] vState = new int[n];
        for (int i = 0; i < n; i++) {
            if (vState[i] != 0) {
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
                    } else if (vState[neighbor] == vState[node]) {
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
