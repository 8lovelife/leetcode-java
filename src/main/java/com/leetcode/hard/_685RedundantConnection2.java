package com.leetcode.hard;

import java.util.Arrays;

public class _685RedundantConnection2 {

    public static void main(String[] args) {
        int[][] edges = new int[][] { { 2, 1 }, { 3, 1 }, { 4, 3 }, { 1, 4 } };
        int[] result = new _685RedundantConnection2().findRedundantConnection(edges);
        System.out.println(Arrays.toString(result));

    }

    // AC: runtime 96.65% mem 88.66%
    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;
        int[] roots = new int[n + 1];
        DisjointSet dSet = new DisjointSet(n + 1);
        for (int i = 1; i <= n; i++) {
            roots[i] = i;
        }

        int[] leadTwoRoots = new int[0];
        int[] leadCycle = new int[0];

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];

            // two roots check
            if (roots[v] != v) {
                leadTwoRoots = edge;
                continue;
            }
            roots[v] = u;

            // cycle check
            if (dSet.isConnected(u, v)) {
                leadCycle = edge;
            }
            dSet.union(u, v);
        }

        if (leadTwoRoots.length == 0) {
            return leadCycle;

        }

        if (leadCycle.length == 0) {
            return leadTwoRoots;
        }

        int midNode = leadTwoRoots[1];
        return new int[] { roots[midNode], midNode };

    }

class DisjointSet {
    private int[] parent;

    DisjointSet(int n) {
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    int findRoot(int v) {
        if (parent[v] == v) {
            return v;
        } 
        parent[v] = findRoot(parent[v]);
        return parent[v];
    }

    void union(int u,int v){
        int uRoot = findRoot(u);
        int vRoot = findRoot(v);
        if (vRoot!=uRoot) {
            parent[uRoot] = vRoot;
        }
    }

    boolean isConnected(int u,int v){
        return findRoot(u) == findRoot(v);
    }

}