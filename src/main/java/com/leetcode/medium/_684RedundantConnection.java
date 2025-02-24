package com.leetcode.medium;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.leetcode.DisjointSet;
import java.util.Queue;

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

    public int[] findRedundantConnectionPostBFS(int[][] edges) {
        int n = edges.length;
        List<Integer>[] graph = new List[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            graph[u].add(v);
            graph[v].add(u);
            if (hasCycleBFS(u, graph)) {
                return edge;
            }
        }
        return new int[0];
    }

    private boolean hasCycleBFS(int node, List<Integer>[] graph){
        boolean[] visited = new boolean[graph.length];
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{node,-1});
        visited[node] = true;
        while(!q.isEmpty()){
            int size = q.size();
            while(size-- > 0){
                int[] cur = q.poll();
                int curNode = cur[0];
                int curParent = cur[1];
                for(int neighbor: graph[curNode]){
                    if(!visited[neighbor]){
                        q.offer(new int[]{neighbor,curNode});
                        visited[neighbor] = true;
                    } else if (neighbor != curParent){
                        return true;
                    }
                }
            }
        }
        return false;
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


    public int[] findRedundantConnectionBFS(int[][] edges) {
        int n = edges.length;
        List<Integer>[] graph = new List[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            if (hasPathBFS(u, v, graph)) {
                return edge;
            }
            graph[u].add(v);
            graph[v].add(u);
        }
        return new int[0];
    }

    public boolean hasPathBFS(int u, int v, List<Integer>[] graph){
        boolean[] visited = new boolean[graph.length];
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(u);
        visited[u] = true;
        while(!q.isEmpty()){
            int size = q.size();
            while(size-- > 0){
                int node = q.poll();
                if(node == v) return true;
                for(int neighbor: graph[node]){
                    if(!visited[neighbor]){
                        q.offer(neighbor);
                        visited[neighbor] = true;
                    }
                }
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