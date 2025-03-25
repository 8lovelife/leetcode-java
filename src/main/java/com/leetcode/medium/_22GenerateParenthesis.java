package com.leetcode.medium;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class _22GenerateParenthesis {

    public List<String> generateParenthesis(int n) {

        char[] parenthesis = { '(', ')' };
        Set<String> result = new HashSet<>();
        StringBuilder sb = new StringBuilder();
        backtracking(parenthesis, result, sb, 0, n);

        List<String> parenthesises = new ArrayList<>();
        for (String str : result) {
            if (isBalanced(str)) {
                parenthesises.add(str);
            }
        }
        return parenthesises;

    }

    private void backtracking(char[] parenthesis, Set<String> result, StringBuilder sb, int currentIndex, int n) {
        String str = sb.toString();
        long leftParenthesis = str.chars().filter(ch -> ch == '(').count();
        long rightParenthesis = str.chars().filter(ch -> ch == ')').count();
        if (leftParenthesis > n || rightParenthesis > n) {
            return;
        }
        if (currentIndex == 2 * n) {
            result.add(sb.toString());
            return;
        }

        for (char c : parenthesis) {
            sb.append(c);
            backtracking(parenthesis, result, sb, currentIndex + 1, n);
            sb.setLength(sb.length() - 1);
        }
    }

    private boolean isBalanced(String parenthesis) {
        Stack<Character> stack = new Stack<>();

        for (char c : parenthesis.toCharArray()) {
            if (c == '(') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }

                stack.pop();
            }
        }

        return stack.isEmpty();
    }

    public List<String> generateParenthesisEfficiency(int n) {

        List<String> result = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        backtrackingEfficiency('(', ')', result, sb, 0, 0, n);
        return result;

    }

    private void backtrackingEfficiency(char openParenthesis, char closeParenthesis, List<String> result,
            StringBuilder sb,
            int leftIndex, int rightIndex,
            int n) {
        if (leftIndex + rightIndex == 2 * n) {
            result.add(sb.toString());
            return;
        }

        if (leftIndex < n) {
            sb.append(openParenthesis);
            backtrackingEfficiency(openParenthesis, closeParenthesis, result, sb, leftIndex + 1, rightIndex, n);
            sb.setLength(sb.length() - 1);
        }

        if (rightIndex < leftIndex) {
            sb.append(closeParenthesis);
            backtrackingEfficiency(openParenthesis, closeParenthesis, result, sb, leftIndex, rightIndex + 1, n);
            sb.setLength(sb.length() - 1);
        }

    }

}
