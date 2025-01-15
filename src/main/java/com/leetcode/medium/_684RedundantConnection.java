package com.leetcode.medium;

public class _684RedundantConnection {

    // AC: runtime 74.40% mem 70.72%
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

    public void union(int a, int b){
        int rootA = find(a);
        int rootB = find(b);
        if (rootA != rootB) {
            parent[rootA] = rootB;
        }
    }

    public Boolean isConnected(int a,int b){
        return find(a) == find(b);
    }
}
