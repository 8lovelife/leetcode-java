package com.leetcode.medium;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

class UnionFind {
    int[] parents;

    UnionFind(int n) {
        parents = new int[n];
        for (int i = 0; i < n; i++) {
            parents[i] = i;
        }
    }

    int union(int u, int v) {
        int uRoot = findRoot(u);
        int vRoot = findRoot(v);
        if (uRoot != vRoot) {
            parents[uRoot] = vRoot;
        }
        return vRoot;
    }

    int findRoot(int node) {
        if (parents[node] == node) {
            return node;
        }
        parents[node] = findRoot(parents[node]);
        return parents[node];
    }

    boolean isConnected(int u, int v) {
        return findRoot(u) == findRoot(v);
    }
}

public class _886PossibleBipartite {

    public boolean possibleBipartitionUnion(int n, int[][] dislikes) {
        List<Integer>[] graph = new List[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] dislike : dislikes) {
            int u = dislike[0];
            int v = dislike[1];
            graph[u].add(v);
            graph[v].add(u);
        }
        UnionFind disjointSet = new UnionFind(n + 1);
        for (int i = 1; i <= n; i++) {
            if (graph[i].size() == 0) {
                continue;
            }
            Integer adjacentRoot = graph[i].stream().reduce((u, v) -> disjointSet.union(u, v)).get();
            if (disjointSet.isConnected(i, adjacentRoot)) {
                return false;
            }
        }
        return true;
    }

    public boolean possibleBipartitionBFS(int n, int[][] dislikes) {
        List<Integer>[] graph = new List[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] dislike : dislikes) {
            int u = dislike[0];
            int v = dislike[1];
            graph[u].add(v);
            graph[v].add(u);
        }
        int[] vStatus = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            if (vStatus[i] != 0) {
                continue;
            }
            Queue<Integer> q = new ArrayDeque<>();
            q.add(i);
            vStatus[i] = 1;
            while (!q.isEmpty()) {
                Integer v = q.poll();
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

    public boolean possibleBipartitionDFS(int n, int[][] dislikes) {
        List<Integer>[] graph = new List[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] dislike : dislikes) {
            int u = dislike[0];
            int v = dislike[1];
            graph[u].add(v);
            graph[v].add(u);
        }
        int[] vStatus = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            if (vStatus[i] != 0) {
                continue;
            }
            if (oddCycleExist(i, 1, vStatus, graph)) {
                return false;
            }
        }
        return true;
    }

    private boolean oddCycleExist(int node, int currentNodeColor, int[] vStatus, List<Integer>[] graph) {
        vStatus[node] = currentNodeColor;
        for (Integer neighbor : graph[node]) {
            if (vStatus[neighbor] == 0) {
                if (oddCycleExist(neighbor, -currentNodeColor, vStatus, graph)) {
                    return true;
                }
            } else if (vStatus[node] == vStatus[neighbor]) {
                return true;
            }
        }
        return false;
    }
}
