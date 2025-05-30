package com.leetcode.medium;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class _207CourseSchedule {

    public static void main(String[] args) {
        new _207CourseSchedule().kahnAlgorithm(4, new int[][] { { 1, 0 }, { 2, 1 }, { 3, 2 }, { 1, 3 } });
    }

    public Boolean canFinishDFS(int numCourses, int[][] prerequisities) {
        List<Integer>[] graph = new List[numCourses];
        for (int i = 0; i < numCourses; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] course : prerequisities) {
            int u = course[1];
            int v = course[0];
            graph[u].add(v);
        }
        int[] visit = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            if (hasCycle(i, visit, graph)) {
                return false;
            }
        }
        return true;
    }

    public boolean hasCycle(int v, int[] visit, List<Integer>[] graph) {
        if (visit[v] == 1) {
            return true;
        }
        if (visit[v] == 2) {
            return false;
        }
        visit[v] = 1;
        for (int neighbor : graph[v]) {
            if (hasCycle(neighbor, visit, graph)) {
                return true;
            }
        }
        visit[v] = 2;
        return false;
    }

    // AC runtime: 80.13% mem: 85.74%
    public Boolean kahnAlgorithm(int numCourses, int[][] prerequisites) {
        List<Integer>[] graph = new List[numCourses];
        for (int i = 0; i < numCourses; i++) {
            graph[i] = new ArrayList<>();
        }
        int[] indegree = new int[numCourses];
        for (int[] pre : prerequisites) {
            graph[pre[1]].add(pre[0]);
            indegree[pre[0]] += 1;
        }

        List<Integer> result = new ArrayList<>(numCourses);
        Queue<Integer> q = new ArrayDeque<>(numCourses);
        for (int i = 0; i < indegree.length; i++) {
            if (indegree[i] == 0) {
                q.add(i);
            }
        }
        while (!q.isEmpty()) {
            int v = q.poll();
            result.add(v);
            for (Integer neighbor : graph[v]) {
                indegree[neighbor] -= 1;
                if (indegree[neighbor] == 0) {
                    q.add(neighbor);
                }
            }
        }
        if (result.size() == numCourses) {
            return true;
        }

        return false;
    }

    public Boolean canFinish(int numCourses, int[][] prerequisites) {
        List<Integer>[] graph = new List[numCourses];
        for (int i = 0; i < numCourses; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] pre : prerequisites) {
            graph[pre[1]].add(pre[0]);
        }
        boolean[] gloableVisited = new boolean[numCourses];
        boolean[] localVisited = new boolean[numCourses];
        for (int i = 0; i < graph.length; i++) {
            if (gloableVisited[i]) {
                continue;
            }
            if (hasCycleOptimize(i, gloableVisited, localVisited, graph)) {
                return false;
            }
        }
        return true;
    }

    // TLE
    public Boolean hasCycle(int currentNode, boolean[] gloableVisited, boolean[] localVisited, List<Integer>[] graph) {
        if (localVisited[currentNode]) {
            return true;
        }
        localVisited[currentNode] = true;
        for (int neighbor : graph[currentNode]) {
            if (hasCycle(neighbor, gloableVisited, localVisited, graph)) {
                return true;
            }
        }
        localVisited[currentNode] = false;
        gloableVisited[currentNode] = true;
        return false;
    }

    // AC runtime: 99.86% mem: 80.20%
    public Boolean hasCycleOptimize(int currentNode, boolean[] gloableVisited, boolean[] localVisited,
            List<Integer>[] graph) {
        if (localVisited[currentNode]) {
            return true;
        }
        if (gloableVisited[currentNode]) {
            return false;
        }
        localVisited[currentNode] = true;
        gloableVisited[currentNode] = true;
        for (int neighbor : graph[currentNode]) {
            if (hasCycle(neighbor, gloableVisited, localVisited, graph)) {
                return true;
            }
        }
        localVisited[currentNode] = false;
        return false;
    }
}
