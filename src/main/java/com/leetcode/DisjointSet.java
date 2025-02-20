package com.leetcode;

public class DisjointSet {
    private int[] parents;
    private int[] ranks;

    public DisjointSet(int n) {
        parents = new int[n];
        ranks = new int[n];
        for (int i = 0; i < n; i++) {
            parents[i] = i;
            ranks[i] = 1;
        }
    }

    public int findRoot(int v) {
        if (parents[v] == v) {
            return v;
        }
        parents[v] = findRoot(parents[v]);
        return parents[v];
    }

    public boolean isConnected(int u, int v) {
        return findRoot(u) == findRoot(v);
    }

    public int union(int u, int v) {
        int uRoot = findRoot(u);
        int vRoot = findRoot(v);
        if (uRoot != vRoot) {
            if (ranks[uRoot] > ranks[vRoot]) {
                parents[vRoot] = uRoot;
            } else if (ranks[uRoot] < ranks[vRoot]) {
                parents[uRoot] = vRoot;
                return vRoot;
            } else {
                parents[vRoot] = uRoot;
                ranks[uRoot]++;
            }
        }
        return uRoot;
    }
}
