package com.leetcode.hard;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Queue;


public class _2608ShortestCycle {


    public int findShortestCyclePathDetectionDFS(int n, int[][] edges){
        List<Integer>[] graph = new List[n];
        for(int i = 0; i < n; i++){
            graph[i] = new ArrayList<>();
        }
        int shortestCycle = Integer.MAX_VALUE;
        for(int[] edge: edges){
            int u = edge[0];
            int v = edge[1];
            List<Integer> cyclePaths = new ArrayList<>();
            int[] depth = new int[n];
            cyclePathDFS(u,v,1,depth,cyclePaths,graph);           
            graph[u].add(v);
            graph[v].add(u);
            if(!cyclePaths.isEmpty()){
                shortestCycle = Math.min(shortestCycle,cyclePaths.stream().min(Integer::compareTo).get());
            }
        }
        return shortestCycle == Integer.MAX_VALUE? -1:shortestCycle;
    }

    public void cyclePathDFS(int u, int v,int step, int[] depth, List<Integer> cyclePaths,List<Integer>[] graph){
        if (u == v) {
            cyclePaths.add(step);
            return;
        }
        depth[u] = step;
        for (int neighbor : graph[u]) {
             // an unvisited node or a previously visited node from a different direction
            if (depth[neighbor] == 0 || depth[neighbor] > depth[u] + 1) {
                cyclePathDFS(neighbor, v, step + 1, depth, cyclePaths, graph);
            }
        }
    }
    


    public int findShortestCycle(int n, int[][] edges) {
        List<Integer>[] graph = new List[n];
        for(int i = 0; i < n; i++){
            graph[i] = new ArrayList<>();
        }
        int shortestCycle = Integer.MAX_VALUE;
        for(int[] edge: edges){
            int u = edge[0];
            int v = edge[1];
            int cyclePath = cyclePath(u,v,graph);
            graph[u].add(v);
            graph[v].add(u);
            shortestCycle = Math.min(shortestCycle,cyclePath);
        }
        return shortestCycle == Integer.MAX_VALUE? -1:shortestCycle + 1;
    }

    private int cyclePath(int u, int v, List<Integer>[] graph){
        boolean[] visited = new boolean[graph.length];
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(u);
        visited[u] = true;
        int step = 0;
        while(!q.isEmpty()){
            int size = q.size();
            while(size-- > 0){
                int node = q.poll();
                if(node == v) return step;
                for(int neighbor: graph[node]){
                    if (!visited[neighbor]){
                        q.offer(neighbor);
                        visited[neighbor] = true;
                    }
                }
            }
            step++;
        }
        return Integer.MAX_VALUE;
    }

  public int findShortestCycle2BFS(int n, int[][] edges) {
    List<Integer>[] graph = new List[n];
    for (int i = 0; i < n; i++) {
        graph[i] = new ArrayList<>();
    }
    for (int[] edge : edges) {
        int u = edge[0];
        int v = edge[1];
        graph[u].add(v);
        graph[v].add(u);
    }

    boolean[] visited = new boolean[n];
    Set<Integer> meetCycleNodes = new HashSet<>();
    for (int i = 0; i < n; i++) {
        if (visited[i]) {
            continue;
        }
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[] { i, -1 });
        visited[i] = true;
        while (!q.isEmpty()) {
            int size = q.size();
            while (size-- > 0) {
                int[] node = q.poll();
                for (int neighbor : graph[node[0]]) {
                    if (!visited[neighbor]) {
                        q.offer(new int[] { neighbor, node[0] });
                        visited[neighbor] = true;
                    } else if (node[1] != neighbor) {
                        meetCycleNodes.add(neighbor);
                    }
                }
            }
        }
    }


    int shortestCycle = Integer.MAX_VALUE;
    for(int node : meetCycleNodes){
        int[] distance = new int[n];
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(node);
        distance[node] = 1;
        while(!q.isEmpty()){
            int size = q.size();
            while(size-- > 0){
                int cur = q.poll();
                for(int neighbor : graph[cur]){
                    if(distance[neighbor] == 0){
                        distance[neighbor] = distance[cur] + 1;
                        q.offer(neighbor);
                    } else if(distance[neighbor] >= distance[cur]){
                        shortestCycle = Math.min(shortestCycle, distance[neighbor] + distance[cur] - 1);
                    }
                }
            }
        }
    }
    return shortestCycle == Integer.MAX_VALUE ? -1 : shortestCycle;
  }

    public int findShortestCycleDFS(int n, int[][] edges) {
        List<Integer>[] graph = new List[n];
        for(int i=0;i < n;i++){
            graph[i] = new ArrayList<>();
        }
        for(int[] edge:edges){
            int u = edge[0];
            int v = edge[1];
            graph[u].add(v);
            graph[v].add(u);
        }
        Set<Integer> cyclePaths = new HashSet<>();
        int[] distance = new int[n];
        for(int node = 0; node < n; node++){
            if(distance[node] != 0){
                continue;
            }
            dfs(node,1,distance,cyclePaths,graph);
        }
        return cyclePaths.isEmpty()? -1:cyclePaths.stream().min(Integer::compareTo).get();
    }

    public void dfs(int u,int depth, int[] distance, Set<Integer> cyclePaths,List<Integer>[] graph){
        distance[u] = depth;
        for(int neighbor: graph[u]){
            // an unvisited node or a previously visited node from a different direction
            if(distance[neighbor] == 0 || distance[neighbor] > distance[u] + 1){
                dfs(neighbor,depth + 1,distance,cyclePaths,graph);
            // cause a cycle here
            // minus 1 to avoid revisiting the parent node    
            } else if(distance[neighbor] < distance[u] - 1){
                cyclePaths.add(distance[u] - distance[neighbor] + 1);
            }
        }
    }

    public int findShortestCycleBFS(int n, int[][] edges) {
        List<Integer>[] graph = new List[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            graph[u].add(v);
            graph[v].add(u);
        }

        int shortestCycle = Integer.MAX_VALUE;
        for (int node = 0; node < n; node++) {
            int[] distance = new int[n];
            Queue<Integer> q = new ArrayDeque<>();
            q.offer(node);
            distance[node] = 1;
            while (!q.isEmpty()) {
                int size = q.size();
                while (size-- > 0) {
                    int cur = q.poll();
                    for (int neighbor : graph[cur]) {
                        if (distance[neighbor] == 0) {
                            distance[neighbor] = distance[cur] + 1;
                            q.offer(neighbor);
                        } else if (distance[neighbor] >= distance[cur]) {
                            shortestCycle = Math.min(shortestCycle, distance[neighbor] + distance[cur] - 1);
                        }
                    }
                }
            }
        }

        return shortestCycle == Integer.MAX_VALUE ? -1 : shortestCycle;
    }
  
}