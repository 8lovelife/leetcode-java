package com.leetcode.easy;

public class _997FindTownJudge {

    public int findJudgeOpt(int n, int[][] trust) {
        int[] trustCount = new int[n + 1];
        for (int[] edge : trust) {
            int u = edge[0];
            int v = edge[1];
            trustCount[u]--;
            trustCount[v]++;
        }
        for (int i = 1; i <= n; i++) {
            if (trustCount[i] == n - 1) {
                return i;
            }
        }
        return -1;
    }

    public int findJudge(int n, int[][] trust) {
        if (n == 1) {
            return 1;
        }
        int[] indegree = new int[n + 1];
        int[] outdegree = new int[n + 1];
        for (int[] edge : trust) {
            int u = edge[0];
            int v = edge[1];
            indegree[v]++;
            outdegree[u]++;
        }
        for (int i = 1; i <= n; i++) {
            if (indegree[i] == n - 1 && outdegree[i] == 0) {
                return i;
            }
        }
        return -1;
    }
}
