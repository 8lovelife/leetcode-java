package com.leetcode.medium;

import java.util.Stack;

public class _2116ParenthesesCanBeValid {

    public boolean canBeValid(String s, String locked) {
        int n = s.length();
        if (n % 2 != 0) {
            return false;
        }

        char[] brackets = s.toCharArray();
        char[] lockedInfo = locked.toCharArray();

        Stack<Integer> lockedBrackets = new Stack<>();
        Stack<Integer> unlockedBrackets = new Stack<>();
        for (int i = 0; i < n; i++) {
            if (lockedInfo[i] == '1') {

                if (brackets[i] == '(') {
                    lockedBrackets.push(i);
                } else if (!lockedBrackets.isEmpty()) {
                    lockedBrackets.pop();
                } else if (!unlockedBrackets.isEmpty()) {
                    unlockedBrackets.pop();
                } else {
                    return false;
                }

            } else {
                unlockedBrackets.push(i);
            }
        }

        while (!lockedBrackets.isEmpty() && !unlockedBrackets.isEmpty()
                && lockedBrackets.peek() < unlockedBrackets.peek()) {
            lockedBrackets.pop();
            unlockedBrackets.pop();
        }

        return lockedBrackets.isEmpty();

    }

    public boolean canBeValidGreedy(String s, String locked) {
        int n = s.length();
        if (n % 2 != 0) {
            return false;
        }
        int openingBalance = 0;
        int closingBalance = 0;
        for (int i = 0; i < n; i++) {
            if (locked.charAt(i) == '0' || s.charAt(i) == '(') {
                openingBalance++;
            } else {
                openingBalance--;
            }

            if (locked.charAt(n - i - 1) == '0' || s.charAt(n - i - 1) == ')') {
                closingBalance++;
            } else {
                closingBalance--;
            }

            if (openingBalance < 0 || closingBalance < 0) {
                return false;
            }
        }
        return true;
    }
}
