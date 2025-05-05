package com.leetcode.easy;

public class _242ValidAnagrams {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        int[] charCounts = new int[26];

        char[] sChars = s.toCharArray();
        char[] tChars = t.toCharArray();

        for (int i = 0; i < tChars.length; i++) {
            charCounts[sChars[i] - 'a']++;
            charCounts[tChars[i] - 'a']--;
        }

        for (int counts : charCounts) {
            if (counts != 0) {
                return false;
            }
        }

        return true;
    }
}
