package com.leetcode.hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _685RedundantConnection2 {

    public static void main(String[] args) {
        int[][] edges = new int[][] { { 1, 2 }, { 2, 3 }, { 3, 4 }, { 4, 1 }, { 1, 5 } };
        int[] result = new _685RedundantConnection2().findRedundantConnectionParentInspect(edges);
        System.out.println(Arrays.toString(result));
    }

    public int[] findRedundantConnectionPostParentInspect(int[][] edges) {
        int n = edges.length;
        int[] parents = new int[n + 1];
        int[] leadTwoRoots = new int[0];
        int[] leadCycle = new int[0];
        for (int i = 0; i < edges.length; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            if (parents[v] != 0) {
                leadTwoRoots = edges[i];
                continue;
            }
            parents[v] = u;
        }
        Arrays.fill(parents, 0);
        for (int[] edge : edges) {
            int v = edge[1];
            int u = edge[0];
            if (parents[v] == 0) {
                parents[v] = u;
            }
            if (hasCycle(v, parents)) {
                leadCycle = edge;
                break;
            }
        }

        if (leadTwoRoots.length == 0) {
            return leadCycle;
        }

        if (leadCycle.length == 0) {
            return leadTwoRoots;
        }

        int v = leadTwoRoots[1];
        return new int[] { parents[v], v };
    }

    private boolean hasCycle(int v, int[] parents) {
        int pre = parents[v];
        while (pre != 0) {
            if (pre == v) {
                return true;
            }
            pre = parents[pre];
        }
        return false;
    }

    public int[] findRedundantConnectionParentInspect(int[][] edges) {
        int n = edges.length;
        int[] parents = new int[n + 1];
        int[] leadTwoRoots = new int[0];
        int[] leadCycle = new int[0];
        for (int i = 0; i < edges.length; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            if (parents[v] != 0) {
                leadTwoRoots = edges[i];
                continue;
            }
            parents[v] = u;

            if (leadCycle.length == 0 && hasCycle(v, parents)) {
                leadCycle = edges[i];
            }
        }

        if (leadTwoRoots.length == 0) {
            return leadCycle;
        }

        if (leadCycle.length == 0) {
            return leadTwoRoots;
        }

        int v = leadTwoRoots[1];
        return new int[] { parents[v], v };
    }

    // inspect cycle in graph with DFS
    public int[] findRedundantConnectionPostDFS(int[][] edges) {
        int n = edges.length;
        List<Integer>[] graph = new List[n + 1];
        int[] leadTwoRoots = new int[0];
        int[] leadCycle = new int[0];
        int[] roots = new int[n + 1];
        int[] visit = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < n; i++) {
            int[] edge = edges[i];
            int u = edge[0];
            int v = edge[1];
            if (roots[v] != 0) {
                leadTwoRoots = edge;
                continue;
            }
            roots[v] = u;
            graph[u].add(v);
        }

        for (int i = edges.length - 1; i >= 0; i--) {
            int v = edges[i][1];
            if (visit[v] == 2) {
                continue;
            }
            if (hasCycle(v, visit, graph)) {
                leadCycle = edges[i];
                break;
            }
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

    // visited value: 0. none visit 1. visiting 2. visited
    private boolean hasCycle(int currentNode, int[] visit, List<Integer>[] graph) {
        if (visit[currentNode] == 1) {
            return true;
        }
        if (visit[currentNode] == 2) {
            return false;
        }
        visit[currentNode] = 1;
        for (int neighbor : graph[currentNode]) {
            if (hasCycle(neighbor, visit, graph)) {
                return true;
            }
        }
        visit[currentNode] = 2;
        return false;
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
        parents[v] = findRoot(parents[v]); // path compression (fully flattened in a single call)
        return parents[v];
    }

    int findRootWhile(int v) {
        while (parents[v] != v) {
            parents[v] = parents[parents[v]]; // path compression (jumps one level in a single call)
            v = parents[v];
        }
        return v;
    }

    void union(int u, int v) {
        int uRoot = findRoot(u);
        int vRoot = findRoot(v);
        if (vRoot != uRoot) {
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

    boolean isConnected(int u, int v) {
        return findRoot(u) == findRoot(v);
    }

}