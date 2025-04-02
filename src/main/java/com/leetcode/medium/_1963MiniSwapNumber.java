package com.leetcode.medium;

public class _1963MiniSwapNumber {

    public int minSwaps(String s) {
        int n = s.length();
        int openingBalance = 0;
        int unbalanced = 0;
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == '[') {
                openingBalance++;
            } else if (openingBalance > 0) {
                openingBalance--;
            } else {
                unbalanced++;
            }
        }
        return (unbalanced + 1) / 2;
    }

    // TLE
    public int minSwapsTLE(String s) {
        int n = s.length();
        char[] chars = s.toCharArray();
        int openingBrackets = 0;
        int closingBrackets = 0;
        int miniSwaps = 0;
        for (int i = 0; i < n; i++) {
            if (chars[i] == '[') {
                openingBrackets++;
            } else {
                closingBrackets++;
            }

            if (closingBrackets > openingBrackets) {
                miniSwaps++;
                closingBrackets--;
                openingBrackets++;
                chars[i] = '[';
                for (int j = n - 1; j > i; j--) {
                    if (chars[j] == '[') {
                        chars[j] = ']';
                        break;
                    }
                }
            }

        }

        return miniSwaps;
    }

}
