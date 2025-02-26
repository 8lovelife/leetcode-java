package com.leetcode.hard;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class _2360LongestCycle {

  public int longestCycle(int[] edges) {
    int n = edges.length;
    int[] visit = new int[n];
    int[] distance = new int[n];
    Set<Integer> cyclePaths = new HashSet<>();
    for (int i = 0; i < n; i++) {
      dfs(i, visit, 1, distance, edges, cyclePaths);
    }
    return cyclePaths.isEmpty() ? -1 : cyclePaths.stream().max(Integer::compareTo).get();
  }

  public void dfs(int u, int[] visit, int depth, int[] distance, int[] edges, Set<Integer> cyclePaths) {
    if (edges[u] == -1) {
      return;
    }
    visit[u] = 1;
    distance[u] = depth;
    int neighbor = edges[u];
    if (visit[neighbor] == 0) {
      dfs(neighbor, visit, depth + 1, distance, edges, cyclePaths);
    } else if (visit[neighbor] == 1) {
      cyclePaths.add(distance[u] - distance[neighbor] + 1);
    }
    visit[u] = 2;
  }

  public int longestCycleDFS(int[] edges) {
    int n = edges.length;
    List<Integer>[] graph = new List[n];
    for (int i = 0; i < n; i++) {
      graph[i] = new ArrayList<>();
    }
    for (int i = 0; i < n; i++) {
      int v = edges[i];
      if (v != -1) {
        graph[i].add(v);
      }
    }
    Set<Integer> cyclePaths = new HashSet<>();
    int[] distance = new int[n];
    int[] visit = new int[n];
    for (int node = 0; node < n; node++) {
      if (visit[node] != 0)
        continue;
      dfs(node, visit, 1, distance, cyclePaths, graph);
    }
    return cyclePaths.isEmpty() ? -1 : cyclePaths.stream().max(Integer::compareTo).get();
  }

  public void dfs(int u, int[] visit, int depth, int[] distance, Set<Integer> cyclePaths, List<Integer>[] graph) {
    distance[u] = depth;
    visit[u] = 1;
    for (int neighbor : graph[u]) {
      if (visit[neighbor] == 0) {
        dfs(neighbor, visit, depth + 1, distance, cyclePaths, graph);
      } else if (visit[neighbor] == 1) {
        cyclePaths.add(distance[u] - distance[neighbor] + 1);
      }
    }
    visit[u] = 2;
  }

}