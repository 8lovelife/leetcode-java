package com.leetcode.medium;

import java.util.Arrays;
import java.util.Stack;

public class _678ValidParentheses {

    public boolean checkValidStringBottomUp(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n + 1][n + 1];
        dp[n][0] = true;
        for (int i = n - 1; i >= 0; i--) {
            for (int openBracket = 0; openBracket < n; openBracket++) {
                boolean isValid = false;
                char c = s.charAt(i);
                if (c == '*') {

                    isValid |= dp[i + 1][openBracket + 1];
                    isValid |= dp[i + 1][openBracket];

                    if (openBracket > 0) {
                        isValid |= dp[i + 1][openBracket - 1];
                    }

                } else if (c == '(') {
                    isValid |= dp[i + 1][openBracket + 1];
                } else if (openBracket > 0) {
                    isValid |= dp[i + 1][openBracket - 1];
                }

                dp[i][openBracket] = isValid;
            }
        }

        return dp[0][0];
    }

    public boolean checkValidStringDPTopDown(String s) {
        int n = s.length();
        int[][] memoization = new int[n][n];
        for (int[] i : memoization) {
            Arrays.fill(i, -1);
        }

        return isValidString(0, 0, memoization, s);
    }

    private boolean isValidString(int index, int openingBrackets, int[][] memoization, String s) {

        if (openingBrackets < 0) {
            return false;
        }

        if (index == s.length()) {
            return openingBrackets == 0;
        }

        int memo = memoization[index][openingBrackets];
        if (memo != -1) {
            return memo == 1;
        }

        boolean isValid = false;
        char c = s.charAt(index);
        if (c == '(') {
            isValid |= isValidString(index + 1, openingBrackets + 1, memoization, s);
        } else if (c == ')') {
            isValid |= isValidString(index + 1, openingBrackets - 1, memoization, s);
        } else {
            isValid |= isValidString(index + 1, openingBrackets + 1, memoization, s);
            isValid |= isValidString(index + 1, openingBrackets - 1, memoization, s);
            isValid |= isValidString(index + 1, openingBrackets, memoization, s);
        }

        memoization[index][openingBrackets] = isValid ? 1 : 0;
        return isValid;
    }

    public boolean checkValidStringGreedy(String s) {
        int n = s.length();
        int openingBrackets = 0;
        int closingBrackets = 0;
        for (int i = 0; i < n; i++) {
            openingBrackets += (s.charAt(i) == '*' || s.charAt(i) == '(') ? 1 : -1;
            closingBrackets += (s.charAt(n - 1 - i) == '*' || s.charAt(n - 1 - i) == ')') ? 1 : -1;
            if (openingBrackets < 0 || closingBrackets < 0) {
                return false;
            }
        }
        return true;
    }

    public boolean checkValidString(String s) {
        int n = s.length();
        Stack<Integer> openingBrackets = new Stack<>();
        Stack<Integer> asterisks = new Stack<>();
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '*') {
                asterisks.push(i);
            } else if (s.charAt(i) == '(') {
                openingBrackets.push(i);
            } else if (!openingBrackets.isEmpty()) {
                openingBrackets.pop();
            } else if (!asterisks.isEmpty()) {
                asterisks.pop();
            } else {
                return false;
            }
        }

        while (!openingBrackets.isEmpty() && !asterisks.isEmpty() && openingBrackets.peek() < asterisks.peek()) {
            openingBrackets.pop();
            asterisks.pop();
        }

        return openingBrackets.isEmpty();
    }

}
