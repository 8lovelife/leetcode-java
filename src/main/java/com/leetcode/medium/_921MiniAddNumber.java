package com.leetcode.medium;

public class _921MiniAddNumber {

    public int minAddToMakeValidBalanced(String s) {
        int n = s.length();
        int openingBalance = 0;
        int unBalanced = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '(') {
                openingBalance++;
            } else if (openingBalance > 0) {
                openingBalance--;
            } else {
                unBalanced++;
            }
        }
        return unBalanced + openingBalance;
    }

    public int minAddToMakeValid(String s) {
        int n = s.length();
        int openingBrackets = 0;
        int closingBrackets = 0;
        int miniAdd = 0;
        for (int i = 0; i < n; i++) {

            if (s.charAt(i) == '(') {
                openingBrackets++;
            } else {
                openingBrackets--;
            }

            if (openingBrackets < 0) {
                miniAdd++;
                openingBrackets = 0;
            }

            if (s.charAt(n - 1 - i) == ')') {
                closingBrackets++;
            } else {
                closingBrackets--;
            }

            if (closingBrackets < 0) {
                miniAdd++;
                closingBrackets = 0;
            }
        }

        return miniAdd;
    }
}
