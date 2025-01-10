package com.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

public class _207CourseSchedule {
    
    public Boolean canFinish(int numCourses,int[][] prerequisites){
        List<Integer>[] graph = new List[numCourses];
        for (int i = 0; i < numCourses; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] pre: prerequisites) {
            graph[pre[1]].add(pre[0]);
        }
        boolean[] gloableVisited = new boolean[numCourses];
        boolean[] localVisited = new boolean[numCourses];
        for (int i = 0; i < graph.length; i++) {
            if (gloableVisited[i]) {
                continue;
            }
            if (hasCycleOptimize(i,gloableVisited,localVisited,graph)) {
                return false;
            }
        }
        return true;
    }

    // TLE
    public Boolean hasCycle(int currentNode,boolean[] gloableVisited,boolean[] localVisited,List<Integer>[] graph) {
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

    public Boolean hasCycleOptimize(int currentNode,boolean[] gloableVisited,boolean[] localVisited,List<Integer>[] graph) {
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
