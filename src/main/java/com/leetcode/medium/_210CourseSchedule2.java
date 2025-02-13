package com.leetcode.medium;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

public class _210CourseSchedule2 {

    public int[] topSortDFS(int numCourses, int[][] prerequisites) {
        List<Integer>[] graph = new List[numCourses];
        for (int i = 0; i < numCourses; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] edge : prerequisites) {
            graph[edge[0]].add(edge[1]);
        }
        List<Integer> result = new ArrayList<>();
        int[] visit = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            if (hasCycle(i, visit, graph, result)) {
                return new int[0];
            }
        }
        if (result.size() != numCourses) {
            return new int[0];
        }
        return result.stream().mapToInt(Integer::intValue).toArray();
    }

    private boolean hasCycle(int v, int[] visit, List<Integer>[] graph, List<Integer> result) {
        if (visit[v] == 1) {
            return true;
        }
        if (visit[v] == 2) {
            return false;
        }
        visit[v] = 1;
        for (int neighbor : graph[v]) {
            if (hasCycle(neighbor, visit, graph, result)) {
                return true;
            }
        }
        visit[v] = 2;
        result.add(v);
        return false;
    }

    public int[] topSortKahnAlgorithm(int numCourses, int[][] prerequisites) {
        List<Integer>[] graph = new List[numCourses];
        for (int i = 0; i < numCourses; i++) {
            graph[i] = new ArrayList<>();
        }
        int[] indegree = new int[numCourses];
        for (int[] edge : prerequisites) {
            graph[edge[1]].add(edge[0]);
            indegree[edge[0]]++;
        }
        List<Integer> topList = new ArrayList<>();
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < indegree.length; i++) {
            if (indegree[i] == 0) {
                queue.add(i);
            }
        }
        while (!queue.isEmpty()) {
            int v = queue.poll();
            topList.add(v);
            for (int neighbor : graph[v]) {
                indegree[neighbor]--;
                if (indegree[neighbor] == 0) {
                    queue.add(neighbor);
                }
            }
        }
        if (topList.size() != numCourses) {
            return new int[0];
        }
        return topList.stream().mapToInt(Integer::intValue).toArray();
    }

    // AC runtime: 5.10% mem: 5.75%
    public int[] findOrderKahnMatrix(int numCourses, int[][] prerequisites) {
        int[][] graph = new int[numCourses][numCourses];
        for (int[] is : graph) {
            Arrays.fill(is, -1);
        }
        int[] indegree = new int[numCourses];
        for (int[] pre : prerequisites) {
            graph[pre[1]][pre[0]] = 1;
            indegree[pre[0]] += 1;
        }
        Queue<Integer> q = new ArrayDeque<>(numCourses);
        for (int i = 0; i < indegree.length; i++) {
            if (indegree[i] == 0) {
                q.add(i);
            }
        }
        List<Integer> result = new ArrayList<>(numCourses);
        while (!q.isEmpty()) {
            int v = q.poll();
            result.add(v);
            for (int i = 0; i < graph.length; i++) {
                int val = graph[v][i];
                if (val == -1) {
                    continue;
                }
                indegree[i] -= 1;
                if (indegree[i] == 0) {
                    q.add(i);
                }
            }
        }
        if (result.size() == numCourses) {
            return result.stream().mapToInt(Integer::intValue).toArray();
        }
        return new int[0];
    }

    // Kahn'algorithm with adjList
    // AC runtime: 54.71% mem: 81.32%
    public int[] findOrderKahn(int numCourses, int[][] prerequisites) {
        List<Integer>[] graph = new List[numCourses];
        for (int i = 0; i < numCourses; i++) {
            graph[i] = new ArrayList<>();
        }
        int[] indegree = new int[numCourses];
        for (int[] pre : prerequisites) {
            graph[pre[1]].add(pre[0]);
            indegree[pre[0]] += 1;
        }

        Queue<Integer> queue = new ArrayDeque<>(numCourses);
        for (int i = 0; i < indegree.length; i++) {
            if (indegree[i] == 0) {
                queue.add(i);
            }
        }

        List<Integer> result = new ArrayList<>();
        while (!queue.isEmpty()) {
            int v = queue.poll();
            result.add(v);
            for (int neighbor : graph[v]) {
                indegree[neighbor] -= 1;
                if (indegree[neighbor] == 0) {
                    queue.add(neighbor);
                }
            }
        }

        if (result.size() == numCourses) {
            return result.stream().mapToInt(Integer::intValue).toArray();
        }

        return new int[0];
    }

    // DFS
    // AC runtime: 91.96% mem: 64.76%
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<Integer>[] graph = new List[numCourses];
        for (int i = 0; i < numCourses; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] prerequisite : prerequisites) {
            graph[prerequisite[0]].add(prerequisite[1]);
        }
        int[] result = new int[numCourses];
        List<Integer> tmpResult = new ArrayList<>();
        boolean[] gloableVisited = new boolean[numCourses];
        boolean[] localVisited = new boolean[numCourses];
        for (int i = 0; i < numCourses; i++) {
            if (hasCycle(i, gloableVisited, localVisited, graph, tmpResult)) {
                return new int[0];
            }
        }
        result = tmpResult.stream().mapToInt(Integer::intValue).toArray();
        return result;
    }

    // DFS
    public Boolean hasCycle(int v, boolean[] gloableVisited, boolean[] localVisited, List<Integer>[] graph,
            List<Integer> tmpResult) {
        if (localVisited[v]) {
            return true;
        }
        if (gloableVisited[v]) {
            return false;
        }
        gloableVisited[v] = true;
        localVisited[v] = true;
        for (int neighbor : graph[v]) {
            if (hasCycle(neighbor, gloableVisited, localVisited, graph, tmpResult)) {
                return true;
            }
        }
        tmpResult.add(v);
        localVisited[v] = false;
        return false;
    }
}
