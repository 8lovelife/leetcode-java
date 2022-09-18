package com.leetcode.easy;

import java.util.Arrays;

public class LengthOfLongestSubstring {

    public static int lengthOfLongestSubstring(String s) {
        int longestLength = 0;
        int[] charArray = new int[256];
        Arrays.fill(charArray, -1);
        int start = 0;
        for (int i = 0; i < s.length(); i++) {
            char charAt = s.charAt(i);
            int presentsIndex = charArray[charAt];
            if (presentsIndex != -1 && presentsIndex >= start) {
                start = presentsIndex + 1;
            }
            charArray[charAt] = i;
            longestLength = Math.max(longestLength, i - start + 1);
        }
        return longestLength;
    }

}
