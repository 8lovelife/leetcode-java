package com.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

public class _210CourseSchedule2 {
    public int[] findOrder(int numCourses,int[][] prerequisites){
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
            if (hasCycle(i,gloableVisited,localVisited,graph,tmpResult)) {
                return new int[0];
            }
        }
        result = tmpResult.stream().mapToInt(Integer::intValue).toArray();
        return result;
    }


    public Boolean hasCycle(int v,boolean[] gloableVisited,boolean[] localVisited,List<Integer>[] graph,List<Integer> tmpResult){
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
