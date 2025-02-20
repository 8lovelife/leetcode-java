package com.leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.leetcode.DisjointSet;

public class _684RedundantConnection {

    public static void main(String[] args) {
        int[][] edges = new int[][] { { 1, 2 }, { 1, 3 }, { 2, 3 } };
        new _684RedundantConnection().findRedundantConnectionPostDFS(edges);
    }

    public int[] findRedundantConnectionPostDFS(int[][] edges) {
        int n = edges.length;
        List<Integer>[] graph = new List[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        boolean[] visited = new boolean[n + 1];
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            graph[u].add(v);
            graph[v].add(u);
            if (hasCycle(-1, v, visited, graph)) {
                return edge;
            }
            visited = new boolean[n + 1];
            // Arrays.fill(visited, false);
        }
        return new int[0];
    }

    private boolean hasCycle(int u, int v, boolean[] visited, List<Integer>[] graph) {
        if (visited[v]) {
            return true;
        }
        visited[v] = true;
        for (int neighbor : graph[v]) {
            if (neighbor == u) {
                continue;
            }
            if (hasCycle(v, neighbor, visited, graph)) {
                return true;
            }
        }
        return false;
    }

    // AC: runtime 28.85% mem 20.11%
    public int[] findRedundantConnectionDFS(int[][] edges) {
        int n = edges.length;
        List<Integer>[] graph = new List[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        boolean[] visited = new boolean[n + 1];
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            if (hasPath(u, v, visited, graph)) {
                return edge;
            }
            graph[u].add(v);
            graph[v].add(u);
        }
        return new int[0];
    }

    public boolean hasPath(int u, int v, boolean[] visited, List<Integer>[] graph) {
        if (u == v) {
            return true;
        }
        if (visited[u]) {
            return false;
        }
        visited[u] = true;
        for (int neighbor : graph[u]) {
            if (hasPath(neighbor, v, visited, graph)) {
                return true;
            }
        }
        visited[u] = false;
        return false;
    }

    // AC: runtime 79.95% mem 99.98%
    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;
        DisjointSet unionFind = new DisjointSet(n + 1);
        for (int[] edge : edges) {
            if (unionFind.isConnected(edge[0], edge[1])) {
                return edge;
            }
            unionFind.union(edge[0], edge[1]);
        }
        return new int[0];
    }
}