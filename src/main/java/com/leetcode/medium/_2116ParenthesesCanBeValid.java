package com.leetcode.medium;

import java.util.Arrays;
import java.util.Stack;

public class _2116ParenthesesCanBeValid {

    public boolean canBeValidDPBottomUp(String s, String locked) {
        int n = s.length();
        if (n % 2 != 0) {
            return false;
        }
        int[][] memorization = new int[n][n];
        for (int[] memo : memorization) {
            Arrays.fill(memo, -1);
        }
        return isValid(s, locked, 0, 0, memorization);

    }

    private boolean isValid(String s, String locked, int index, int openingBrackets, int[][] memorization) {
        if (index == s.length()) {
            return openingBrackets == 0;
        }

        if (openingBrackets < 0) {
            return false;
        }

        if (memorization[index][openingBrackets] != -1) {
            return memorization[index][openingBrackets] == 1;
        }

        boolean isValid = false;
        if (locked.charAt(index) == '0') {
            isValid |= isValid(s, locked, index + 1, openingBrackets - 1, memorization);
            isValid |= isValid(s, locked, index + 1, openingBrackets + 1, memorization);
        } else if (s.charAt(index) == '(') {
            isValid |= isValid(s, locked, index + 1, openingBrackets + 1, memorization);
        } else {
            isValid |= isValid(s, locked, index + 1, openingBrackets - 1, memorization);
        }

        memorization[index][openingBrackets] = isValid ? 1 : 0;

        return isValid;
    }

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
