package com.leetcode.hard;

import java.util.Collections;
import java.util.PriorityQueue;

public class _871MinimumRefuelStops {

    // nlogn
    public int minRefuelStopsPq(int target, int startFuel, int[][] stations) {
        int n = stations.length;
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>(Collections.reverseOrder());
        int curFuel = startFuel, stops = 0, i = 0;

        while (curFuel < target) {
            while (i < n && curFuel >= stations[i][0]) {
                pq.offer(stations[i][1]);
                i++;
            }

            if (pq.isEmpty()) {
                return -1;
            }

            curFuel += pq.poll();
            stops++;
        }

        return stops;

    }

    // n squared
    public int minRefuelStops(int target, int startFuel, int[][] stations) {

        int n = stations.length;
        int[] dp = new int[n + 1];
        dp[0] = startFuel; // maximum distance with i stops

        for (int i = 0; i < n; i++) {
            int distance = stations[i][0];
            int fuel = stations[i][1];
            for (int j = i; j >= 0; j--) { // start bottom up here
                if (dp[j] >= distance) {
                    dp[j + 1] = Math.max(dp[j + 1], dp[j] + fuel);
                }
            }
        }

        for (int i = 0; i <= n; i++) {
            if (dp[i] >= target) {
                return i;
            }
        }

        return -1;

    }
}
