package com.leetcode.medium;

public class _1541MiniInsertionNumber {

    public int minInsertions(String s) {
        int n = s.length();
        int openingBalance = 0;
        int unBalanced = 0;
        int i = 0;
        while (i < n) {
            if (s.charAt(i) == '(') {
                openingBalance++;
                i++;
            } else {
                if (i + 1 < n && s.charAt(i + 1) == ')') { // two ')'
                    if (openingBalance > 0) {
                        openingBalance--;
                    } else {
                        unBalanced++;
                    }
                    i += 2;
                } else { // one ')'
                    if (openingBalance > 0) {
                        openingBalance--;
                        unBalanced++;
                    } else {
                        unBalanced += 2;
                    }

                    i++;
                }

            }

        }

        return unBalanced + openingBalance * 2;

    }

    public int minInsertionsSimplified(String s) {
        int n = s.length();
        int insertions = 0;
        int currentNeedClosing = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '(') {
                if (currentNeedClosing % 2 == 1) {
                    insertions++;
                    currentNeedClosing--;
                }
                currentNeedClosing += 2;
            } else {
                currentNeedClosing--;
                if (currentNeedClosing < 0) {
                    insertions++;
                    currentNeedClosing = 1;
                }

            }
        }
        return insertions + currentNeedClosing;
    }
}
