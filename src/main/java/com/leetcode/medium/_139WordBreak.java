package com.leetcode.medium;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class _139WordBreak {

    // TLE (Time Limited Exceeded)
    public boolean wordBreak(String s, List<String> wordDict) {

        Queue<Integer> q = new ArrayDeque<>();
        q.offer(0);
        while (!q.isEmpty()) {
            int start = q.poll();
            if (start == s.length()) {
                return true;
            }
            for (int i = start + 1; i <= s.length(); i++) {
                String subString = s.substring(start, i);
                if (wordDict.contains(subString)) {
                    q.offer(i);
                }
            }
        }
        return false;
    }

    // BFS with optimization
    public boolean wordBreakOptimization(String s, List<String> wordDict) {
        Set<String> words = new HashSet<>(wordDict);
        Queue<Integer> q = new ArrayDeque<>();
        boolean[] visited = new boolean[s.length() + 1];
        q.offer(0);
        while (!q.isEmpty()) {
            int start = q.poll();
            if (visited[start]) {
                continue;
            }
            if (start == s.length()) {
                return true;
            }
            for (int i = start + 1; i <= s.length(); i++) {
                String subString = s.substring(start, i);
                if (words.contains(subString)) {
                    q.offer(i);
                }
            }
            visited[start] = true;
        }
        return false;
    }

}
