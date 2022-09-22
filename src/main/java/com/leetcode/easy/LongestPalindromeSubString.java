package com.leetcode.easy;

public class LongestPalindromeSubString {

    public static String lpsExtendAroundPossibleCenter(String s) {
        String lps = "";
        int length = s.length();
        for (int i = 0; i < length; i++) {
            String oddLps = extendAroundCenterLength(s, i, i);
            String evenLps = extendAroundCenterLength(s, i, i + 1);
            String tmpLps = oddLps.length() > evenLps.length() ? oddLps : evenLps;
            if (tmpLps.length() > lps.length()) {
                lps = tmpLps;
            }
        }
        return lps;
    }

    public static String extendAroundCenterLength(String s, Integer start, Integer end) {
        int left = start;
        int right = end;
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return s.substring(left + 1, right);
    }

    public static String longestPalindromeSubStringBruteForce(String s) {
        String lps = "";
        int length = s.length();
        for (int i = 0; i < length; i++) {
            for (int j = i; j < length; j++) {
                boolean isPalindrome = true;
                int middle = (j - i + 1) / 2;
                for (int k = 0; k < middle; k++) {
                    if (s.charAt(k + i) != s.charAt(j - k)) {
                        isPalindrome = false;
                        break;
                    }
                }
                int palindromeLength = j - i + 1;
                if (isPalindrome && palindromeLength > lps.length()) {
                    lps = s.substring(i, j + 1);
                }
            }
        }
        return lps;
    }
}