package com.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

public class _17LetterCombinationOfPhoneNumber {
    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();

        if (digits.isBlank())
            return result;

        String[] numberLetters = { "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };
        StringBuilder sb = new StringBuilder();
        backtracking(digits, 0, result, sb, numberLetters);
        return result;

    }

    private void backtracking(String digits, Integer currentIndex, List<String> result, StringBuilder sb,
            String[] numberLetters) {
        if (currentIndex == digits.length()) {
            result.add(sb.toString());
            return;
        }

        int index = digits.charAt(currentIndex) - '0';
        for (char letter : numberLetters[index].toCharArray()) {
            sb.append(letter);
            backtracking(digits, currentIndex + 1, result, sb, numberLetters);
            sb.setLength(sb.length() - 1);
        }

    }
}
