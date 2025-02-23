package com.leetcode.hard;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;


public class _2608ShortestCycle {


public int findShortestCycle(int n, int[][] edges) {
    List<Integer>[] graph = new List[n];
    for(int i = 0; i < n; i++){
        graph[i] = new ArrayList<>();
    }
    for(int[] edge: edges){
        int u = edge[0];
        int v = edge[1];

        
    }
      
    return -1;
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

    boolean[] visited = new boolean[n];
    List<Integer> meetCycleNodes = new ArrayList<>();
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
  
}