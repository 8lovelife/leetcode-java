package com.leetcode.medium;

public class _1541MiniInsertionNumber {
    public int minInsertions(String s) {
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
