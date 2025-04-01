package com.leetcode.medium;

public class _1249MinRemoveBeParentheses {

    public String minRemoveToMakeValid(String s) {
        int n = s.length();
        int openingBrackets = 0;
        int closingBrackets = 0;
        char[] chars = s.toCharArray();
        for (int i = 0; i < n; i++) {
            if (chars[i] == '(') {
                openingBrackets++;
            } else if (chars[i] == ')') {
                openingBrackets--;
            }

            if (openingBrackets < 0) {
                chars[i] = '0';
                openingBrackets = 0;
            }

            if (chars[n - 1 - i] == ')') {
                closingBrackets++;
            } else if (chars[n - 1 - i] == '(') {
                closingBrackets--;
            }

            if (openingBrackets < 0) {
                chars[i] = '0';
                openingBrackets = 0;
            }

            if (closingBrackets < 0) {
                chars[n - 1 - i] = '0';
                closingBrackets = 0;
            }

        }

        StringBuilder sb = new StringBuilder();
        for (char c : chars) {
            if (c != '0') {
                sb.append(c);
            }
        }

        String result = sb.toString();
        return result;
    }
}
