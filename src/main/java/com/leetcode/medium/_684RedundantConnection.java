package com.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

public class _684RedundantConnection {

    public static void main(String[] args) {
        int[][] edges = new int[][] { { 1, 2 }, { 1, 3 }, { 2, 3 } };
        new _684RedundantConnection().findRedundantConnectionDFS(edges);
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
            // a little optimization
            // if (graph[u].isEmpty() || graph[v].isEmpty()) {
            // continue;
            // }

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
        UnionFind unionFind = new UnionFind(n);
        for (int[] edge : edges) {
            if (unionFind.isConnected(edge[0], edge[1])) {
                return edge;
            }
            unionFind.union(edge[0], edge[1]);
        }
        return new int[0];
    }
}

class UnionFind {

    private int[] parent;

    UnionFind(int n) {
        parent = new int[n + 1];
        for (int i = 1; i < parent.length; i++) {
            parent[i] = i;
        }
    }

    public int find(int a) {
        if (parent[a] == a) {
            return a;
        }
        return find(parent[a]);
    }

    public int findWithPathCompression(int a) {
        if (parent[a] == a) {
            return a;
        } else {
            parent[a] = findWithPathCompression(parent[a]);
            return parent[a];
        }
    }

    public void union(int a, int b) {
        int rootA = findWithPathCompression(a);
        int rootB = findWithPathCompression(b);
        if (rootA != rootB) {
            parent[rootA] = rootB;
        }
    }

    public Boolean isConnected(int a, int b) {
        return findWithPathCompression(a) == findWithPathCompression(b);
    }
}
