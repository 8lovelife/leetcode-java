package com.leetcode.easy;

import java.util.ArrayList;
import java.util.List;

public class _2273RemoveAnagrams {
    public List<String> removeAnagrams(String[] words) {
        List<String> result = new ArrayList<>();

        if (words.length == 1) {
            result.add(words[0]);
            return result;
        }

        result.add(words[0]);
        for (int i = 1; i < words.length; i++) {

            if (!isAnagrams(words[i], words[i - 1])) {
                result.add(words[i]);
            }

        }

        return result;
    }

    private boolean isAnagrams(String w1, String w2) {

        if (w1.length() != w2.length()) {
            return false;
        }

        int[] charCounts = new int[26];

        char[] w1C = w1.toCharArray();
        char[] w2C = w2.toCharArray();

        for (int i = 0; i < w1C.length; i++) {
            charCounts[w1C[i] - 'a']++;
            charCounts[w2C[i] - 'a']--;
        }

        for (int counts : charCounts) {
            if (counts != 0) {
                return false;
            }
        }

        return true;
    }
}
