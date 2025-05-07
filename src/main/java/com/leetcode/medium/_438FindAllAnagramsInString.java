package com.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

public class _438FindAllAnagramsInString {

    public List<Integer> findAnagrams3(String s, String p) {
        List<Integer> ans = new ArrayList<>();
        int n = s.length();
        int windowsSize = p.length();
        if (windowsSize > n) {
            return ans;
        }

        int[] counts = new int[26];
        char[] sC = s.toCharArray();
        char[] pC = p.toCharArray();
        for (int i = 0; i < windowsSize; i++) {
            counts[pC[i] - 'a']++;
            counts[sC[i] - 'a']--;
        }

        if (allCountsZero(counts)) {
            ans.add(0);
        }

        for (int j = windowsSize; j < n; j++) {
            int slideOutIndex = j - windowsSize;
            counts[sC[slideOutIndex] - 'a']++;
            counts[sC[j] - 'a']--;
            if (allCountsZero(counts)) {
                ans.add(slideOutIndex + 1);
            }
        }

        return ans;

    }

    /**
     * O( SL + PL )
     * 
     * @param s
     * @param p
     * @return
     */
    public List<Integer> findAnagrams2(String s, String p) {
        List<Integer> result = new ArrayList<>();
        int sL = s.length();
        int pL = p.length();
        if (sL < pL) {
            return result;
        }
        char[] pC = p.toCharArray();
        char[] sC = s.toCharArray();
        int[] counts = new int[27];
        for (int i = 0; i < pC.length; i++) {
            counts[pC[i] - 'a']++;
            counts[sC[i] - 'a']--;
        }

        if (allCountsZero(counts)) {
            result.add(0);
        }

        for (int j = pL; j < sL; j++) {
            counts[sC[j] - 'a']--;
            counts[sC[j - pL] - 'a']++;
            if (allCountsZero(counts)) {
                result.add(j - pL + 1);
            }
        }

        return result;

    }

    private boolean allCountsZero(int[] counts) {
        for (int count : counts) {
            if (count != 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * O( SL * PL )
     */
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();
        int sL = s.length();
        int pL = p.length();
        if (sL < pL) {
            return result;
        }
        char[] pC = p.toCharArray();
        char[] sC = s.toCharArray();
        for (int i = 0; i < sL - pL + 1; i++) {
            if (isAnagrams(i, sC, pC)) {
                result.add(i);
            }
        }
        return result;

    }

    private boolean isAnagrams(int index, char[] sC, char[] pC) {
        int[] counts = new int[27];
        for (int i = 0; i < pC.length; i++) {
            counts[pC[i] - 'a']++;
            counts[sC[i + index] - 'a']--;
        }
        for (int count : counts) {
            if (count != 0) {
                return false;
            }
        }
        return true;
    }
}
