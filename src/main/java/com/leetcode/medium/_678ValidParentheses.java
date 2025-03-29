package com.leetcode.medium;

import java.util.Stack;

public class _678ValidParentheses {

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
