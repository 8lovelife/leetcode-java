package com.leetcode.hard;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

import com.leetcode.DisjointSet;

public class _2493DivideNodesIntoMaxGroups {

    public static void main(String[] args) {
        // n = 6, edges = [[1,2],[1,4],[1,5],[2,6],[2,3],[4,6]]
        int[][] edges = new int[][] { { 1, 2 } };
        int diameter = new _2493DivideNodesIntoMaxGroups().magnificentSets(6, edges);
        System.out.println(diameter);
    }

    public int magnificentSets(int n, int[][] edges) {
        List<Integer>[] graph = new List[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        DisjointSet unionFind = new DisjointSet(n + 1);
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            graph[u].add(v);
            graph[v].add(u);
            unionFind.union(u, v);
        }
        DisjointSet disjointSet = new DisjointSet(n + 1);
        for (int i = 1; i <= n; i++) {
            if (graph[i].isEmpty()) {
                continue;
            }
            int adjacentRoot = graph[i].stream().reduce((u, v) -> disjointSet.union(u, v)).get();
            if (disjointSet.isConnected(i, adjacentRoot)) {
                return -1;
            }
        }

        int[] nodeGroups = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            int diameterOfNode = diameter(i, graph);
            int associatedGraph = unionFind.findRoot(i);
            int maxGroupsOfNode = Math.max(nodeGroups[associatedGraph], diameterOfNode);
            nodeGroups[associatedGraph] = maxGroupsOfNode;
        }
        return Arrays.stream(nodeGroups).sum();
    }

    public int diameter(int currentNode, List<Integer>[] graph) {
        int levels = 0;
        boolean[] visited = new boolean[graph.length];
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(currentNode);
        visited[currentNode] = true;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int j = 0; j < size; j++) {
                int levelNode = q.poll();
                for (Integer neighbor : graph[levelNode]) {
                    if (visited[neighbor]) {
                        continue;
                    }
                    q.add(neighbor);
                    visited[neighbor] = true;
                }
            }
            levels++;
        }
        return levels;
    }

    // public int diameter(int n, List<Integer>[] graph) {
    // int maxLevels = 0;
    // for (int i = 1; i <= n; i++) {
    // int levels = 0;
    // boolean[] visited = new boolean[n + 1];
    // Queue<Integer> q = new ArrayDeque<>();
    // q.add(i);
    // visited[i] = true;
    // while (!q.isEmpty()) {
    // int size = q.size();
    // for (int j = 0; j < size; j++) {
    // int levelNode = q.poll();
    // for (Integer neighbor : graph[levelNode]) {
    // if (visited[neighbor]) {
    // continue;
    // }
    // q.add(neighbor);
    // visited[neighbor] = true;
    // }
    // }
    // levels++;
    // }
    // maxLevels = Math.max(levels, maxLevels);
    // }
    // return maxLevels;
    // }

}
