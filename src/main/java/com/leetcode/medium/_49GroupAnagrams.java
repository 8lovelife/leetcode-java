package com.leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class _49GroupAnagrams {
    public List<List<String>> groupAnagrams(String[] strs) {

        Map<Integer, List<String>> groups = new HashMap<>();
        for (String word : strs) {
            int groupKey = orderedWord(word);
            groups.computeIfAbsent(groupKey, k -> new ArrayList<>()).add(word);
        }

        return new ArrayList<>(groups.values());

    }

    private int orderedWord(String word) {

        int[] counts = new int[26];

        for (char c : word.toCharArray()) {
            counts[c - 'a']++;
        }

        return Arrays.hashCode(counts);

    }
}
