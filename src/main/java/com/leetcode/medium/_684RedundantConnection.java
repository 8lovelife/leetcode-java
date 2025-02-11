package com.leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

class DisjointSet {
    private int[] parents;
    private int[] ranks;

    DisjointSet(int n) {
        parents = new int[n];
        ranks = new int[n];
        for (int i = 0; i < n; i++) {
            parents[i] = i;
            ranks[i] = 1;
        }
    }

    int findRoot(int v) {
        if (parents[v] == v) {
            return v;
        }
        parents[v] = findRoot(parents[v]);
        return parents[v];
    }

    boolean isConnected(int u, int v) {
        return findRoot(u) == findRoot(v);
    }

    void union(int u, int v) {
        int uRoot = findRoot(u);
        int vRoot = findRoot(v);
        if (uRoot != vRoot) {
            if (ranks[uRoot] > ranks[vRoot]) {
                parents[vRoot] = uRoot;
            } else if (ranks[uRoot] < ranks[vRoot]) {
                parents[uRoot] = vRoot;
            } else {
                parents[vRoot] = uRoot;
                ranks[uRoot]++;
            }
        }
    }
}

class UnionFind {

    private int[] parents;
    private int[] ranks;

    UnionFind(int n) {
        parents = new int[n + 1];
        for (int i = 1; i < parents.length; i++) {
            parents[i] = i;
        }
    }

    public int find(int a) {
        if (parents[a] == a) {
            return a;
        }
        return find(parents[a]);
    }

    public int findWithPathCompression(int a) {
        if (parents[a] == a) {
            return a;
        } else {
            parents[a] = findWithPathCompression(parents[a]);
            return parents[a];
        }
    }

    public void union(int a, int b) {
        int rootA = findWithPathCompression(a);
        int rootB = findWithPathCompression(b);
        if (rootA != rootB) {
            parents[rootA] = rootB;
        }
    }

    public Boolean isConnected(int a, int b) {
        return findWithPathCompression(a) == findWithPathCompression(b);
    }
}
