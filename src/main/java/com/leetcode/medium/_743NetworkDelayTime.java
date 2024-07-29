package com.leetcode.medium;

import java.util.*;

public class _743NetworkDelayTime {


    public static void main(String[] args) {
        int[][] times = {{2,1,1},{2,3,1},{3,4,1}};
        int result = networkDelayTimeAdjList(times,4,2);
        System.out.println(result);

    }


    public static int networkDelayTimeAdjList(int[][] times,int n,int k){

        int INF = 0x3f3f3f3f;
        List[] adjLists = new List[n];
        for (int[] time : times) {
            int u = time[0];
            int v = time[1];
            int w = time[2];
            List<int[]> adjList = adjLists[u - 1];
            if (adjList == null) {
                List<int[]> newAdjList = new ArrayList();
                adjLists[u - 1] = newAdjList;
                newAdjList.add(new int[] { v - 1, w });
            } else {
                adjLists[u - 1].add(new int[] { v - 1, w });
            }
        }

        int[] distance = new int[n];
        Arrays.fill(distance, INF);
        boolean[] visited = new boolean[n];
        distance[k - 1] = 0;

        for (int i = 1; i < n; i++) {
            int miniV = findMiniDistance(distance, visited, n);
            visited[miniV] = true;
            List<int[]> adjVs = adjLists[miniV];
            if (adjVs == null) {
                continue;
            }
            for (int[] adjV : adjVs) {
                int v = adjV[0];
                int w = adjV[1];
                int adjDistance = distance[miniV] + w;
                if (!visited[v] && adjDistance < distance[v]) {
                    distance[v] = adjDistance;
                }
            }
        }

        int result = -1;
        for (int index = 0; index < n; index++) {
            if (distance[index] == INF) {
                return -1;
            }
            if (distance[index] > result) {
                result = distance[index];
            }
            
        }
        return result;
    }

    // adjacency matrix
    public static int networkDelayTime(int[][] times, int n, int k) {

        // init adjacency matrix
        int INF = 0x3f3f3f3f; // INF representation
        int[][] matrix = new int[n][n];
        for(int[] fillMatrix:matrix){
            Arrays.fill(fillMatrix,INF);
        }
        for(int[] time:times){
            int u = time[0];
            int v = time[1];
            int w = time[2];
            matrix[u-1][v-1] = w;
        }

        int[] distance = new int[n];
        Arrays.fill(distance,INF);
        boolean[] visited = new boolean[n];
        distance[k-1] = 0;

        for(int i = 1;i<n;i++) {
            int miniV = findMiniDistance(distance,visited,n);
            visited[miniV] = true;
            for(int j = 0;j < n;j ++) {
                int adjDistance = distance[miniV] + matrix[miniV][j];
                if(!visited[j] && adjDistance < distance[j]){
                    distance[j] = adjDistance;
                }
            }
        }

        int result = -1;
        for(int i = 0;i < n;i++){
            if(distance[i] == INF){
                return -1;
            }
            if(result < distance[i]) {
               result = distance[i];
            }
        }
        return result;

    }

    private static int findMiniDistance(int[] distance,boolean[] visited,int n){
        int miniDistance = 0x3f3f3f3f;
        int index = 0;
        for(int i = 0;i<n;i++){
            if(!visited[i] && distance[i] < miniDistance) {
                miniDistance = distance[i];
                index = i;
            }
        }
        return index;
    }
        

    
}
