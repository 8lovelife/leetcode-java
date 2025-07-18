package com.leetcode.medium;

import java.util.ArrayDeque;
import java.util.Arrays;
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

    // BFS with optimization ( n * n * n + m * k)
    public boolean wordBreakOptimization(String s, List<String> wordDict) {
        Set<String> words = new HashSet<>(wordDict); // m * k
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

    // n * m * k
    public boolean wordBreakTopDownDFS(String s, List<String> wordDict) {
        int[] mem = new int[s.length()];
        Arrays.fill(mem, -1);
        return dp(s.length() - 1, mem, wordDict, s); // n is the state of the s indexs
    }

    private boolean dp(int endIndex, int[] mem, List<String> wordDict, String s) {
        if (endIndex < 0) {
            return true;
        }
        if (mem[endIndex] != -1) {
            return mem[endIndex] == 1;
        }
        for (String word : wordDict) { // m is the length of wordDict
            int startIndex = endIndex - word.length() + 1;
            if (startIndex < 0) {
                continue;
            }

            if (s.substring(startIndex, endIndex + 1).equals(word) // k is the length of s
                    && dp(startIndex - 1, mem, wordDict, s)) {
                mem[endIndex] = 1;
                return true;
            }
        }
        mem[endIndex] = 0;
        return false;
    }

    // n * m * k
    public boolean wordBreakBottomUpDP(String s, List<String> wordDict) {
        int n = s.length();
        boolean[] dp = new boolean[n];
        for (int i = 0; i < n; i++) {
            for (String word : wordDict) {
                int wordL = word.length();
                if (i < wordL - 1) {
                    continue;
                }
                if (i == wordL - 1 || dp[i - wordL]) {
                    if (s.substring(i - wordL + 1, i + 1).equals(word)) {
                        dp[i] = true;
                        break;
                    }
                }
            }
        }
        return dp[n - 1];
    }

    public boolean wordBreakBottomUpDp2(String s, List<String> wordDict) {
        int n = s.length();
        Set<String> words = new HashSet<>(wordDict);
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && words.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[n];
    }

}
