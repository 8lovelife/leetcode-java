package com.leetcode.easy;

import java.util.Stack;

public class _20ValidParentheses {

    public boolean isValid(String s) {
        if (s.length() % 2 != 0) {
            return false;
        }

        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else {

                if (stack.isEmpty())
                    return false;

                char closingBracket = stack.pop();
                if ((closingBracket == '{' && c == '}') || (closingBracket == '(' && c == ')')
                        || (closingBracket == '[' && c == ']')) {
                    continue;
                } else {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }

    public boolean isValidCleanWay(String s) {
        int n = s.length();
        if (n % 2 != 0) {
            return false;
        }

        int[] brackets = new int[n];
        int index = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                brackets[index++] = ')';
            } else if (c == '{') {
                brackets[index++] = '}';
            } else if (c == '[') {
                brackets[index++] = ']';
            } else {
                if (index == 0 || brackets[--index] != c) {
                    return false;
                }
            }

        }

        return index == 0;
    }
}
