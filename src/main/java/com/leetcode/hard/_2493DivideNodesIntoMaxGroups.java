package com.leetcode.hard;

import com.leetcode.DisjointSet;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;


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

        // detect whether the graph is bipartite
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
        // calculate the diameter of each set
        int[] nodeGroups = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            int diameterOfNode = longestShortestPath(i, graph);
            int associatedGraph = unionFind.findRoot(i);
            int maxGroupsOfNode = Math.max(nodeGroups[associatedGraph], diameterOfNode);
            nodeGroups[associatedGraph] = maxGroupsOfNode;
        }
        // calculate the total groups of the graph
        return Arrays.stream(nodeGroups).sum();
    }


    public int longestShortestPath(int currentNode, List<Integer>[] graph) {
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

    public int longestShortestPathWhileCheckingBipartite(int node, List<Integer>[] graph){
        int levels = 0;
        int[] vStatus = new int[graph.length];
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(node);
        vStatus[node] = 1;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int j = 0; j < size; j++) {
                int levelNode = q.poll();
                for (Integer neighbor : graph[levelNode]) {
                    if (vStatus[neighbor] == 0) {
                        q.add(neighbor);
                        vStatus[neighbor] = -vStatus[levelNode];
                    } else if(vStatus[neighbor] == vStatus[levelNode]){
                        return -1;
                    }
                }
            }
            levels++;
        }
        return levels;
    }

    public int diameter(List<Integer> groupSets, List<Integer>[] graph){
        int diameter = 0;
        for (int i = 1; i < groupSets.size(); i++) {
            int diameterOfNode = longestShortestPath(i, graph);
            diameter = Math.max(diameter, diameterOfNode);
        }
        return diameter;
    }

    public int magnificentSets2BFS(int n, int[][] edges) {
        List<Integer>[] graph = new List[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            graph[u].add(v);
            graph[v].add(u);
        }

        int[] vStatus = new int[n + 1];
        int maxGroups = 0;
        for (int i = 1; i <= n; i++) {
            if (vStatus[i] != 0)
                continue;
            Queue<Integer> q = new ArrayDeque<>();
            q.offer(i);
            vStatus[i] = 1;
            List<Integer> groupSets = new ArrayList<>();
            while (!q.isEmpty()) {
                int size = q.size();
                for (int j = 0; j < size; j++) {
                    int levelNode = q.poll();
                    groupSets.add(levelNode);
                    for (Integer neighbor : graph[levelNode]) {
                        if (vStatus[neighbor] == 0) {
                            q.offer(neighbor);
                            vStatus[neighbor] = -vStatus[levelNode];
                        } else if (vStatus[neighbor] == vStatus[levelNode]) {
                            return -1;
                        }
                    }
                }
            }

            int diameter = 0;
            for (int node : groupSets) {
                int diameterOfNode = longestShortestPath(node, graph);
                diameter = Math.max(diameter, diameterOfNode);
            }
            maxGroups += diameter;
        }
        return maxGroups;
    }

    
    public int magnificentSetsBFSUnionFind(int n, int[][] edges) {
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

        int[] vStatus = new int[n + 1];
        int[] diameterOfSets = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            if (vStatus[i] != 0){
                vStatus = new int[n + 1];
            }
            Queue<Integer> q = new ArrayDeque<>();
            q.offer(i);
            vStatus[i] = 1;
            int longestShorestPath = 0;
            while (!q.isEmpty()) {
                int size = q.size();
                for (int j = 0; j < size; j++) {
                    int levelNode = q.poll();
                    for (Integer neighbor : graph[levelNode]) {
                        if (vStatus[neighbor] == 0) {
                            q.offer(neighbor);
                            vStatus[neighbor] = -vStatus[levelNode];
                        } else if (vStatus[neighbor] == vStatus[levelNode]) {
                            return -1;
                        }
                    }
                }
                longestShorestPath ++;
            }
            int group = unionFind.findRoot(i);
            diameterOfSets[group] = Math.max(diameterOfSets[group], longestShorestPath);
        }
        return Arrays.stream(diameterOfSets).sum();
}

    public int magnificentSetsDFSBFS(int n, int[][] edges) {
        List<Integer>[] graph = new List[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            graph[u].add(v);
            graph[v].add(u);
        }

        int[] nodeLongestShorestPath = new int[n + 1];
        for(int i = 1; i <= n; i++){
            int levels = 0;
            int[] vStatus = new int[graph.length];
            Queue<Integer> q = new ArrayDeque<>();
            q.offer(i);
            vStatus[i] = 1;
            while (!q.isEmpty()) {
                int size = q.size();
                for (int j = 0; j < size; j++) {
                    int levelNode = q.poll();
                    for (Integer neighbor : graph[levelNode]) {
                        if (vStatus[neighbor] == 0) {
                            q.add(neighbor);
                            vStatus[neighbor] = -vStatus[levelNode];
                        } else if(vStatus[neighbor] == vStatus[levelNode]){
                            return -1;
                        }
                    }
                }
                levels++;
            }
            nodeLongestShorestPath[i] = levels;
        }

        int totalGroups = 0;
        boolean[] visited = new boolean[n + 1];
        for(int i = 1; i <= n; i++){
            if(visited[i]) continue;
            totalGroups += diameter(i,visited,nodeLongestShorestPath,graph);
        }
        return totalGroups;
    }

    public int diameter(int node, boolean[] visited,int[] nodeLongestShorestPath,List<Integer>[] graph){
        int diameter = nodeLongestShorestPath[node];
        visited[node] = true;
        for(int neighbor : graph[node]){
            if(visited[neighbor]){
                continue;
            }
           diameter = Math.max(diameter,diameter(neighbor,visited,nodeLongestShorestPath,graph));
        }
        return diameter;
    }

}